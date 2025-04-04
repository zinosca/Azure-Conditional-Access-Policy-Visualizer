import React, { useEffect, useState } from 'react';
import { Box } from '@mui/material';

interface Cloud {
  id: number;
  x: number;
  y: number;
  size: number;
  color: string;
  opacity: number;
  velocity: number;
  blur: number;
  direction: 'left' | 'right';
}

const CloudEffect: React.FC = () => {
  const [clouds, setClouds] = useState<Cloud[]>([]);

  useEffect(() => {
    const colors = [
      'rgba(255, 0, 0, 0.15)',    // Red
      'rgba(255, 165, 0, 0.15)',  // Orange
      'rgba(255, 255, 0, 0.15)',  // Yellow
      'rgba(0, 255, 0, 0.15)',    // Green
      'rgba(0, 0, 255, 0.15)',    // Blue
      'rgba(75, 0, 130, 0.15)',   // Indigo
      'rgba(238, 130, 238, 0.15)', // Violet
    ];

    const createCloud = () => {
      const direction = Math.random() < 0.5 ? 'left' : 'right';
      const newCloud: Cloud = {
        id: Date.now(),
        x: direction === 'left' ? -100 : window.innerWidth + 100,
        y: Math.random() * window.innerHeight,
        size: Math.random() * 300 + 200,
        color: colors[Math.floor(Math.random() * colors.length)],
        opacity: 0,
        velocity: Math.random() * 0.3 + 0.1,
        blur: Math.random() * 30 + 20,
        direction,
      };

      setClouds(prev => [...prev, newCloud]);

      // Fade in
      setTimeout(() => {
        setClouds(prev => 
          prev.map(c => c.id === newCloud.id ? { ...c, opacity: 0.4 } : c)
        );
      }, 50);

      // Fade out and remove
      setTimeout(() => {
        setClouds(prev => 
          prev.map(c => c.id === newCloud.id ? { ...c, opacity: 0 } : c)
        );
        setTimeout(() => {
          setClouds(prev => prev.filter(c => c.id !== newCloud.id));
        }, 1000);
      }, 6000);
    };

    const interval = setInterval(createCloud, 2000);

    return () => clearInterval(interval);
  }, []);

  return (
    <Box
      sx={{
        position: 'fixed',
        top: 0,
        left: 0,
        right: 0,
        bottom: 0,
        pointerEvents: 'none',
        zIndex: 0,
        overflow: 'hidden',
      }}
    >
      {clouds.map(c => (
        <Box
          key={c.id}
          sx={{
            position: 'absolute',
            left: c.x,
            top: c.y,
            width: c.size,
            height: c.size,
            borderRadius: '50%',
            background: `radial-gradient(circle at center, ${c.color} 0%, transparent 70%)`,
            opacity: c.opacity,
            transition: 'opacity 1s ease-in-out',
            filter: `blur(${c.blur}px)`,
            animation: c.direction === 'left' 
              ? `floatLeft ${c.velocity}s linear infinite`
              : `floatRight ${c.velocity}s linear infinite`,
            transform: 'translate(-50%, -50%)',
          }}
        />
      ))}
    </Box>
  );
};

export default CloudEffect; 