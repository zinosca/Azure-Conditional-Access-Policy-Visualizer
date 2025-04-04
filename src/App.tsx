import React, { useState } from 'react';
import { 
  Container, 
  Paper, 
  Typography, 
  Box, 
  Button,
  TextField,
  Grid,
  Alert,
  ThemeProvider,
  createTheme,
  CssBaseline,
  keyframes
} from '@mui/material';
import { Upload as UploadIcon } from '@mui/icons-material';
import PolicyVisualization from './components/PolicyVisualization';
import CloudEffect from './components/ConfettiEffect';
import { Policy, AccessTestInput } from './types/policy';
import PolicyList from './components/PolicyList';
import FileUpload from './components/FileUpload';

// Create floating animations
const floatLeft = keyframes`
  0% {
    transform: translateX(0) translateY(0) rotate(0deg);
  }
  50% {
    transform: translateX(100px) translateY(-20px) rotate(180deg);
  }
  100% {
    transform: translateX(0) translateY(0) rotate(360deg);
  }
`;

const floatRight = keyframes`
  0% {
    transform: translateX(0) translateY(0) rotate(0deg);
  }
  50% {
    transform: translateX(-100px) translateY(-20px) rotate(180deg);
  }
  100% {
    transform: translateX(0) translateY(0) rotate(360deg);
  }
`;

// Create a dark theme with Microsoft-like colors
const theme = createTheme({
  palette: {
    mode: 'dark',
    primary: {
      main: '#0078D4', // Microsoft blue
      light: '#2B88D9',
      dark: '#106EBE',
    },
    secondary: {
      main: '#00B7C3', // Microsoft cyan
      light: '#33C5CE',
      dark: '#009DA7',
    },
    background: {
      default: '#1E1E1E', // VS Code-like dark
      paper: '#2D2D2D',
    },
    text: {
      primary: '#FFFFFF',
      secondary: '#CCCCCC',
    },
  },
  typography: {
    fontFamily: '"Segoe UI", "Roboto", "Arial", sans-serif',
    h4: {
      fontWeight: 600,
    },
    h6: {
      fontWeight: 500,
    },
  },
  components: {
    MuiPaper: {
      styleOverrides: {
        root: {
          backgroundImage: 'linear-gradient(45deg, #2D2D2D 0%, #3D3D3D 100%)',
          backdropFilter: 'blur(10px)',
          border: '1px solid rgba(255, 255, 255, 0.1)',
        },
      },
    },
    MuiButton: {
      styleOverrides: {
        root: {
          textTransform: 'none',
          borderRadius: '4px',
          padding: '8px 16px',
          fontWeight: 500,
          background: 'linear-gradient(45deg, #0078D4 0%, #2B88D9 100%)',
          '&:hover': {
            background: 'linear-gradient(45deg, #106EBE 0%, #0078D4 100%)',
          },
        },
      },
    },
    MuiTextField: {
      styleOverrides: {
        root: {
          '& .MuiOutlinedInput-root': {
            '& fieldset': {
              borderColor: 'rgba(255, 255, 255, 0.2)',
            },
            '&:hover fieldset': {
              borderColor: 'rgba(255, 255, 255, 0.3)',
            },
            '&.Mui-focused fieldset': {
              borderColor: '#0078D4',
            },
            '& input': {
              color: '#FFFFFF',
            },
          },
          '& .MuiInputLabel-root': {
            color: 'rgba(255, 255, 255, 0.7)',
            '&.Mui-focused': {
              color: '#0078D4',
            },
          },
        },
      },
    },
    MuiCssBaseline: {
      styleOverrides: `
        @keyframes floatLeft {
          0% {
            transform: translateX(0) translateY(0) rotate(0deg);
          }
          50% {
            transform: translateX(100px) translateY(-20px) rotate(180deg);
          }
          100% {
            transform: translateX(0) translateY(0) rotate(360deg);
          }
        }
        @keyframes floatRight {
          0% {
            transform: translateX(0) translateY(0) rotate(0deg);
          }
          50% {
            transform: translateX(-100px) translateY(-20px) rotate(180deg);
          }
          100% {
            transform: translateX(0) translateY(0) rotate(360deg);
          }
        }
      `,
    },
  },
});

function App() {
  const [policies, setPolicies] = useState<Policy[]>([]);
  const [selectedPolicy, setSelectedPolicy] = useState<Policy | null>(null);
  const [error, setError] = useState<string | null>(null);
  const [testInput, setTestInput] = useState<AccessTestInput>({
    location: '',
    user: '',
    application: '',
    device: '',
    role: ''
  });

  const validatePolicy = (policy: any): Policy => {
    // Ensure required fields exist
    if (!policy.DisplayName || !policy.State || !policy.Conditions) {
      throw new Error('Policy is missing required fields (DisplayName, State, or Conditions)');
    }

    // Ensure required condition objects exist
    const requiredConditions = [
      'Applications', 'Locations', 'Users', 'Platforms', 'Devices'
    ];

    requiredConditions.forEach(condition => {
      if (!policy.Conditions[condition]) {
        policy.Conditions[condition] = {};
      }
    });

    return policy as Policy;
  };

  const handleFileUpload = (event: React.ChangeEvent<HTMLInputElement>) => {
    const file = event.target.files?.[0];
    if (!file) return;

    const reader = new FileReader();
    reader.onload = (e) => {
      try {
        const content = e.target?.result as string;
        const parsedData = JSON.parse(content);
        
        // Handle both single policy and array of policies
        const policiesArray = Array.isArray(parsedData) ? parsedData : [parsedData];
        
        // Validate and process each policy
        const validatedPolicies = policiesArray.map(validatePolicy);
        
        setPolicies(validatedPolicies);
        setSelectedPolicy(validatedPolicies[0]);
        setError(null);
      } catch (err) {
        setError(err instanceof Error ? err.message : 'Error parsing JSON file. Please ensure the file contains valid Azure Conditional Access Policies.');
      }
    };
    reader.readAsText(file);
  };

  const handleTestAccess = () => {
    if (!selectedPolicy) return;
    
    const result = evaluateAccess(selectedPolicy, testInput);
    setError(result);
  };

  const evaluateAccess = (policy: Policy, input: AccessTestInput): string => {
    const conditions = policy.Conditions;
    
    // Check location conditions
    if (conditions.Locations.IncludeLocations.length > 0) {
      const locationMatch = conditions.Locations.IncludeLocations.some(loc => 
        loc.toLowerCase().includes(input.location.toLowerCase())
      );
      if (!locationMatch) {
        return 'Access denied: Location not included';
      }
    }
    
    if (conditions.Locations.ExcludeLocations.some(loc => 
      loc.toLowerCase().includes(input.location.toLowerCase())
    )) {
      return 'Access denied: Location excluded';
    }
    
    // Check user conditions
    if (conditions.Users.IncludeUsers.length > 0 && !conditions.Users.IncludeUsers.includes('All')) {
      const userMatch = conditions.Users.IncludeUsers.some(user => 
        user.toLowerCase().includes(input.user.toLowerCase())
      );
      if (!userMatch) {
        return 'Access denied: User not included';
      }
    }
    
    if (conditions.Users.ExcludeUsers.some(user => 
      user.toLowerCase().includes(input.user.toLowerCase())
    )) {
      return 'Access denied: User excluded';
    }
    
    // Check application conditions
    if (conditions.Applications.IncludeApplications.length > 0 && !conditions.Applications.IncludeApplications.includes('All')) {
      const appMatch = conditions.Applications.IncludeApplications.some(app => 
        app.toLowerCase().includes(input.application.toLowerCase())
      );
      if (!appMatch) {
        return 'Access denied: Application not included';
      }
    }
    
    if (conditions.Applications.ExcludeApplications.some(app => 
      app.toLowerCase().includes(input.application.toLowerCase())
    )) {
      return 'Access denied: Application excluded';
    }
    
    return 'Access granted';
  };

  const handlePoliciesUploaded = (newPolicies: Policy[]) => {
    setPolicies(prev => [...prev, ...newPolicies]);
    if (newPolicies.length > 0 && !selectedPolicy) {
      setSelectedPolicy(newPolicies[0]);
    }
  };

  return (
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <CloudEffect />
      <Box sx={{ 
        minHeight: '100vh',
        background: 'linear-gradient(135deg, #1E1E1E 0%, #2D2D2D 100%)',
        py: 4,
        position: 'relative',
        zIndex: 1
      }}>
        <Container maxWidth="lg">
          <Typography 
            variant="h4" 
            component="h1" 
            gutterBottom 
            align="center"
            sx={{
              color: '#FFFFFF',
              textShadow: '0 0 10px rgba(0, 120, 212, 0.5)',
              mb: 4
            }}
          >
            Azure Conditional Access Policy Visualizer
          </Typography>

          <Box sx={{ display: 'flex', gap: 2, height: '100%' }}>
            <Box sx={{ display: 'flex', flexDirection: 'column', gap: 2, width: 300 }}>
              <FileUpload onFilesAccepted={handlePoliciesUploaded} />
              <PolicyList
                policies={policies}
                selectedPolicy={selectedPolicy}
                onPolicySelect={setSelectedPolicy}
              />
            </Box>
            <Box sx={{ flex: 1 }}>
              {selectedPolicy ? (
                <PolicyVisualization policy={selectedPolicy} />
              ) : (
                <Box
                  sx={{
                    height: '100%',
                    display: 'flex',
                    alignItems: 'center',
                    justifyContent: 'center',
                    bgcolor: '#1a1a1a',
                    borderRadius: 2,
                    color: 'white',
                  }}
                >
                  Select or upload a policy to visualize
                </Box>
              )}
            </Box>
          </Box>

          {error && (
            <Alert severity="error" sx={{ mb: 2 }}>
              {error}
            </Alert>
          )}

          {selectedPolicy && (
            <Grid container spacing={3}>
              <Grid item xs={12} md={8}>
                <Paper sx={{ p: 2, height: '100%' }}>
                  <PolicyVisualization 
                    policy={selectedPolicy} 
                    testInput={testInput}
                  />
                </Paper>
              </Grid>
              
              <Grid item xs={12} md={4}>
                <Paper sx={{ p: 2, height: '100%' }}>
                  <Typography variant="h6" gutterBottom sx={{ color: '#FFFFFF' }}>
                    Test Access
                  </Typography>
                  
                  <Box sx={{ display: 'flex', flexDirection: 'column', gap: 2 }}>
                    <TextField
                      label="Location"
                      value={testInput.location}
                      onChange={(e) => setTestInput({ ...testInput, location: e.target.value })}
                      fullWidth
                    />
                    <TextField
                      label="User"
                      value={testInput.user}
                      onChange={(e) => setTestInput({ ...testInput, user: e.target.value })}
                      fullWidth
                    />
                    <TextField
                      label="Application"
                      value={testInput.application}
                      onChange={(e) => setTestInput({ ...testInput, application: e.target.value })}
                      fullWidth
                    />
                    <TextField
                      label="Device"
                      value={testInput.device}
                      onChange={(e) => setTestInput({ ...testInput, device: e.target.value })}
                      fullWidth
                    />
                    <TextField
                      label="Role"
                      value={testInput.role}
                      onChange={(e) => setTestInput({ ...testInput, role: e.target.value })}
                      fullWidth
                    />
                    
                    <Button
                      variant="contained"
                      onClick={handleTestAccess}
                      fullWidth
                      sx={{
                        background: 'linear-gradient(45deg, #00B7C3 0%, #33C5CE 100%)',
                        '&:hover': {
                          background: 'linear-gradient(45deg, #009DA7 0%, #00B7C3 100%)',
                        }
                      }}
                    >
                      Test Access
                    </Button>
                  </Box>
                </Paper>
              </Grid>
            </Grid>
          )}
        </Container>
      </Box>
    </ThemeProvider>
  );
}

export default App; 