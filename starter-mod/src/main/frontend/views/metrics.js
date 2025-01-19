import { jsx as _jsx, jsxs as _jsxs } from "react/jsx-runtime";
import React, { Component } from 'react';
import { ServerHealthService } from 'Frontend/generated/endpoints';
export const config = { menu: { order: 7, icon: 'line-awesome/svg/file.svg' }, title: 'Metrics' };
class MetricsView extends Component {
    constructor(props) {
        super(props);
        this.state = {
            metrics: null
        };
    }
    async componentDidMount() {
        const data = await ServerHealthService.getMetrics();
        this.setState({ metrics: data });
    }
    render() {
        const { metrics } = this.state;
        return (_jsxs("div", { className: "flex flex-col h-full items-center justify-center p-l text-center box-border", children: [_jsx("h1", { children: "Server Metrics" }), metrics ? (_jsx("pre", { children: JSON.stringify(metrics, null, 2) })) : (_jsx("p", { children: "Loading..." }))] }));
    }
}
export default MetricsView;
//# sourceMappingURL=metrics.js.map