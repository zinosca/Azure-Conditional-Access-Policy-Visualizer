import React, { useEffect, useRef } from 'react';
import { Policy } from '../types/policy';
import { Box } from '@mui/material';
import * as d3 from 'd3';

interface TestInput {
  location: string;
  user: string;
  application: string;
  device: string;
  role: string;
}

interface PolicyVisualizationProps {
  policy: Policy;
  width?: number;
  height?: number;
  testInput?: TestInput;
}

interface Circle {
  name: string;
  color: string;
  outerRadius: number;
  innerRadius: number;
  x: number;
  y: number;
  include: string[];
  exclude: string[];
}

const PolicyVisualization: React.FC<PolicyVisualizationProps> = ({
  policy,
  width = 800,
  height = 600,
  testInput
}) => {
  const svgRef = useRef<SVGSVGElement>(null);
  const mainGroupRef = useRef<d3.Selection<SVGGElement, unknown, null, undefined> | null>(null);
  const intersectionGroupRef = useRef<d3.Selection<SVGGElement, unknown, null, undefined> | null>(null);

  useEffect(() => {
    if (!svgRef.current) return;

    const svg = d3.select(svgRef.current);
    svg.selectAll("*").remove();

    // Define circles with include/exclude conditions
    const circles: Circle[] = [
      {
        name: 'Locations',
        color: '#4CAF50',
        outerRadius: 150,
        innerRadius: 120,
        x: 200,
        y: 300,
        include: policy.Conditions.Locations?.IncludeLocations || [],
        exclude: policy.Conditions.Locations?.ExcludeLocations || []
      },
      {
        name: 'Users',
        color: '#2196F3',
        outerRadius: 150,
        innerRadius: 120,
        x: 400,
        y: 300,
        include: policy.Conditions.Users?.IncludeUsers || [],
        exclude: policy.Conditions.Users?.ExcludeUsers || []
      },
      {
        name: 'Applications',
        color: '#FFC107',
        outerRadius: 150,
        innerRadius: 120,
        x: 300,
        y: 200,
        include: policy.Conditions.Applications?.IncludeApplications || [],
        exclude: policy.Conditions.Applications?.ExcludeApplications || []
      }
    ];

    // Create main group for all elements
    mainGroupRef.current = svg.append("g");

    // Create circle groups
    const circleGroups = mainGroupRef.current.selectAll("g")
      .data(circles)
      .enter()
      .append("g")
      .attr("transform", d => `translate(${d.x},${d.y})`);

    // Add outer circles (exclude)
    circleGroups.append("circle")
      .attr("r", d => d.outerRadius)
      .attr("fill", d => d.color)
      .attr("opacity", d => {
        if (testInput) {
          switch (d.name) {
            case 'Locations':
              return testInput.location && d.include.includes(testInput.location) ? 0.6 : 0.2;
            case 'Users':
              return testInput.user && d.include.includes(testInput.user) ? 0.6 : 0.2;
            case 'Applications':
              return testInput.application && d.include.includes(testInput.application) ? 0.6 : 0.2;
            default:
              return 0.2;
          }
        }
        return 0.2;
      })
      .attr("stroke", d => d.color)
      .attr("stroke-width", 2)
      .attr("stroke-dasharray", d => d.exclude.length > 0 ? "5,5" : "none");

    // Add inner circles (include)
    circleGroups.append("circle")
      .attr("r", d => d.innerRadius)
      .attr("fill", d => d.color)
      .attr("opacity", 0.4)
      .attr("stroke", d => d.color)
      .attr("stroke-width", 2)
      .attr("stroke-dasharray", d => d.include.length > 0 ? "5,5" : "none");

    // Add labels
    circleGroups.append("text")
      .attr("text-anchor", "middle")
      .attr("dy", d => -d.outerRadius - 10)
      .attr("fill", "white")
      .attr("font-size", "14px")
      .text(d => d.name);

    // Add condition labels
    circleGroups.each(function(d) {
      const group = d3.select(this);
      
      // Add include label if there are include conditions
      if (d.include.length > 0) {
        group.append("text")
          .attr("text-anchor", "middle")
          .attr("dy", -d.innerRadius + 15)
          .attr("fill", "white")
          .attr("font-size", "12px")
          .text(d.include.join(", "));
      }

      // Add exclude label if there are exclude conditions
      if (d.exclude.length > 0) {
        group.append("text")
          .attr("text-anchor", "middle")
          .attr("dy", -d.outerRadius + 15)
          .attr("fill", "white")
          .attr("font-size", "12px")
          .text(d.exclude.join(", "));
      }
    });

    // Create intersection group
    intersectionGroupRef.current = mainGroupRef.current.append("g");

    // Calculate and draw intersections
    circles.forEach((circle1, i) => {
      circles.slice(i + 1).forEach((circle2) => {
        // Calculate outer intersection first
        const outerIntersection = calculateIntersection(
          circle1,
          circle2,
          circle1.outerRadius,
          circle2.outerRadius
        );

        if (outerIntersection) {
          intersectionGroupRef.current!.append("path")
            .attr("d", outerIntersection)
            .attr("fill", "rgba(255, 255, 255, 0.1)")
            .attr("stroke", "white")
            .attr("stroke-width", 1);
        }

        // Then calculate inner intersection
        const innerIntersection = calculateIntersection(
          circle1,
          circle2,
          circle1.innerRadius,
          circle2.innerRadius
        );

        if (innerIntersection) {
          intersectionGroupRef.current!.append("path")
            .attr("d", innerIntersection)
            .attr("fill", "rgba(255, 255, 255, 0.3)")
            .attr("stroke", "white")
            .attr("stroke-width", 2);
        }
      });
    });

    return () => {
      svg.selectAll("*").remove();
    };
  }, [policy, testInput]);

  return (
    <Box className="policy-visualization-container" style={{ 
      width: `${width}px`, 
      height: `${height}px`, 
      backgroundColor: '#1a1a1a', 
      borderRadius: '8px', 
      overflow: 'hidden' 
    }}>
      <svg ref={svgRef} width={width} height={height} style={{ background: '#1a1a1a' }} />
    </Box>
  );
};

const calculateIntersection = (circle1: Circle, circle2: Circle, radius1: number, radius2: number): string | null => {
  const d = Math.sqrt(
    Math.pow(circle2.x - circle1.x, 2) +
    Math.pow(circle2.y - circle1.y, 2)
  );

  // Return null if circles don't intersect
  if (d > radius1 + radius2) return null;
  // Return null if one circle is completely inside the other
  if (d < Math.abs(radius1 - radius2)) return null;

  const a = (radius1 * radius1 - radius2 * radius2 + d * d) / (2 * d);
  const h = Math.sqrt(radius1 * radius1 - a * a);

  const x2 = circle1.x + (a * (circle2.x - circle1.x)) / d;
  const y2 = circle1.y + (a * (circle2.y - circle1.y)) / d;

  const x3 = x2 + (h * (circle2.y - circle1.y)) / d;
  const y3 = y2 - (h * (circle2.x - circle1.x)) / d;

  const x4 = x2 - (h * (circle2.y - circle1.y)) / d;
  const y4 = y2 + (h * (circle2.x - circle1.x)) / d;

  return `M ${x3} ${y3} A ${radius1} ${radius1} 0 0 1 ${x4} ${y4} A ${radius2} ${radius2} 0 0 1 ${x3} ${y3}`;
};

export default PolicyVisualization; 