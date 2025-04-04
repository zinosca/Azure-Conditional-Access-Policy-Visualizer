import * as React from 'react';
import { useCallback } from 'react';
import { Box, Typography, Button } from '@mui/material';
import { Policy } from '../types/policy';

interface FileUploadProps {
  onFilesAccepted: (policies: Policy[]) => void;
}

const FileUpload: React.FC<FileUploadProps> = ({ onFilesAccepted }) => {
  const handleFileChange = useCallback((event: React.ChangeEvent<HTMLInputElement>) => {
    const files = event.target.files;
    if (!files || files.length === 0) {
      console.log('No files selected');
      return;
    }

    console.log('Files selected:', files.length);
    const policies: Policy[] = [];
    let processedFiles = 0;
    
    Array.from(files).forEach(file => {
      console.log('Processing file:', file.name);
      const reader = new FileReader();
      
      reader.onload = () => {
        try {
          const content = reader.result as string;
          console.log('File content loaded');
          const policy = JSON.parse(content) as Policy;
          console.log('Policy parsed:', policy);
          policies.push(policy);
          processedFiles++;
          
          console.log(`Processed ${processedFiles} of ${files.length} files`);
          if (processedFiles === files.length) {
            console.log('All files processed, calling onFilesAccepted');
            onFilesAccepted(policies);
          }
        } catch (error) {
          console.error(`Error parsing file ${file.name}:`, error);
          processedFiles++;
          
          if (processedFiles === files.length) {
            console.log('All files processed (with errors), calling onFilesAccepted');
            onFilesAccepted(policies);
          }
        }
      };

      reader.onerror = (error) => {
        console.error('Error reading file:', error);
        processedFiles++;
        
        if (processedFiles === files.length) {
          console.log('All files processed (with errors), calling onFilesAccepted');
          onFilesAccepted(policies);
        }
      };

      reader.readAsText(file);
    });
  }, [onFilesAccepted]);

  return (
    <Box sx={{ textAlign: 'center', p: 3 }}>
      <input
        type="file"
        accept=".json"
        multiple
        onChange={handleFileChange}
        style={{ display: 'none' }}
        id="file-upload"
      />
      <label htmlFor="file-upload">
        <Button
          variant="contained"
          component="span"
          sx={{ mb: 2 }}
        >
          Select Policy Files
        </Button>
      </label>
      <Typography variant="body2" color="text.secondary">
        Only JSON files are accepted
      </Typography>
    </Box>
  );
};

export default FileUpload; 