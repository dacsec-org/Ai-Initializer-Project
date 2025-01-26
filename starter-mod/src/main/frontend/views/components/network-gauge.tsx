import React, { useEffect, useState } from 'react';
// @ts-ignore
import { RadialGauge } from 'react-canvas-gauges';

interface NetworkGaugeProps {
  value?: number;
}

const NetworkGauge: React.FC<NetworkGaugeProps> = ({ value: propValue }) => {
  const [value, setValue] = useState(0);

  useEffect(() => {
    const interval = setInterval(() => {
      // Fetch Network stats and update value
      setValue(Math.random() * 100); // Replace with actual data fetching logic
    }, 1000);

    return () => clearInterval(interval);
  }, []);

  return (
    <RadialGauge
      value={propValue ?? value}
      maxValue={100}
      title="Network Usage"
      units="%"
      colorPlate="#222"
      colorMajorTicks="#f5f5f5"
      colorMinorTicks="#ddd"
      colorTitle="#fff"
      colorUnits="#ccc"
      colorNumbers="#eee"
      colorNeedleStart="rgba(240, 128, 128, 1)"
      colorNeedleEnd="rgba(255, 160, 122, .9)"
      needleCircleSize={15}
      needleCircleOuter={true}
      needleCircleInner={false}
      animationDuration={1500}
      animationRule="linear"
    />
  );
};

export default NetworkGauge;
