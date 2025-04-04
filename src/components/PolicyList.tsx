import React from 'react';
import { Box, List, ListItem, ListItemText, ListItemButton, Typography, Paper } from '@mui/material';
import { Policy } from '../types/policy';

interface PolicyListProps {
  policies: Policy[];
  selectedPolicy: Policy | null;
  onPolicySelect: (policy: Policy) => void;
}

const PolicyList: React.FC<PolicyListProps> = ({ policies, selectedPolicy, onPolicySelect }) => {
  return (
    <Paper 
      elevation={3} 
      sx={{ 
        width: 300, 
        height: '100%', 
        bgcolor: '#1a1a1a',
        overflow: 'auto'
      }}
    >
      <Box className="policy-list-box">
        {/* @ts-ignore */}
        <Box sx={{ padding: '16px' }}>
          <Typography variant="h6" color="white" gutterBottom>
            Uploaded Policies
          </Typography>
        </Box>
        <List>
          {policies.map((policy, index) => (
            <ListItem key={index} disablePadding>
              <ListItemButton 
                selected={selectedPolicy?.DisplayName === policy.DisplayName}
                onClick={() => onPolicySelect(policy)}
                sx={{
                  '&.Mui-selected': {
                    backgroundColor: 'rgba(255, 255, 255, 0.1)',
                    '&:hover': {
                      backgroundColor: 'rgba(255, 255, 255, 0.15)',
                    },
                  },
                }}
              >
                <ListItemText 
                  primary={policy.DisplayName}
                  secondary={`${policy.Conditions.Locations?.IncludeLocations?.length || 0} Locations, ${policy.Conditions.Users?.IncludeUsers?.length || 0} Users`}
                  primaryTypographyProps={{ color: 'white' }}
                  secondaryTypographyProps={{ color: 'rgba(255, 255, 255, 0.7)' }}
                />
              </ListItemButton>
            </ListItem>
          ))}
        </List>
      </Box>
    </Paper>
  );
};

export default PolicyList; 