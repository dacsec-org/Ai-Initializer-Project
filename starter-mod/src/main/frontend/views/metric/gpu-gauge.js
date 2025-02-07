import { jsx as _jsx } from "react/jsx-runtime";
import { useEffect, useState } from 'react';
import { RadialGauge } from '@progress/kendo-react-gauges';
import { Metrics } from './Metrics';
import { MetricsTypes } from 'Frontend/enums/MetricsTypes';
const GpuGauge = ({ value: propValue }) => {
    const [value, setValue] = useState(0);
    const fetchData = async () => {
        const data = await Metrics.getMetrics(MetricsTypes.GPU);
        setValue(data.value);
    };
    useEffect(() => {
        fetchData().then(r => console.log(r));
        const intervalId = setInterval(() => fetchData(), 1000);
        return () => clearInterval(intervalId);
    }, []);
    const gaugeOptions = {
        value: propValue ?? value,
        scale: {
            max: 100,
            majorUnit: 20,
            minorUnit: 2,
            ranges: [
                { from: 0, to: 40, color: '#28a745' },
                { from: 40, to: 70, color: '#ffc107' },
                { from: 70, to: 100, color: '#dc3545' }
            ]
        },
        pointer: {
            value: propValue ?? value
        }
    };
    return _jsx(RadialGauge, { ...gaugeOptions });
};
export default GpuGauge;
//# sourceMappingURL=gpu-gauge.js.map