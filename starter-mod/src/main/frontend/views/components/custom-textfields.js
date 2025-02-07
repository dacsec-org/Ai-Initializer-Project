import { jsx as _jsx, jsxs as _jsxs } from "react/jsx-runtime";
import { Icon, TextField, Tooltip } from '@vaadin/react-components';
import { Component } from 'react';
class CustomTextFields extends Component {
    renderTextField(fieldNumber) {
        switch (fieldNumber) {
            case 1:
                return (_jsxs(TextField, { label: "Model location", helperText: "add custom LLM location(not recomended)", placeholder: "default: /home/USER/project-ai-initializer/models", clearButtonVisible: true, children: [_jsx(Tooltip, { slot: "tooltip", text: "Tooltip text 1" }), _jsx(Icon, { slot: "prefix", icon: "vaadin:vaadin-h" }), _jsx("span", { slot: "suffix", children: ":)" })] }));
            case 2:
                return (_jsxs(TextField, { label: "Snapshots location", helperText: "add custom LLM snapshot location(not recomended)", placeholder: "default: /home/USER/project-ai-initializer/models/.snapshots", clearButtonVisible: true, children: [_jsx(Tooltip, { slot: "tooltip", text: "Tooltip text 2" }), _jsx(Icon, { slot: "prefix", icon: "vaadin:vaadin-h" }), _jsx("span", { slot: "suffix", children: ":)" })] }));
            case 3:
                return (_jsxs(TextField, { label: "path", helperText: "enter path to directory", placeholder: "/path/to/directory", clearButtonVisible: true, children: [_jsx(Tooltip, { slot: "tooltip", text: "Tooltip text 3" }), _jsx(Icon, { slot: "prefix", icon: "vaadin:vaadin-h" }), _jsx("span", { slot: "suffix", children: ":)" })] }));
            case 4:
                return (_jsxs(TextField, { label: "file path", helperText: "enter path to file", placeholder: "/path/to/file.*", clearButtonVisible: true, children: [_jsx(Tooltip, { slot: "tooltip", text: "Tooltip text 4" }), _jsx(Icon, { slot: "prefix", icon: "vaadin:vaadin-h" }), _jsx("span", { slot: "suffix", children: ":)" })] }));
            case 5:
                return (_jsxs(TextField, { label: "File 5", helperText: "Helper text 5", placeholder: "Placeholder 5", clearButtonVisible: true, children: [_jsx(Tooltip, { slot: "tooltip", text: "Tooltip text 5" }), _jsx(Icon, { slot: "prefix", icon: "vaadin:vaadin-h" }), _jsx("span", { slot: "suffix", children: ":)" })] }));
            case 6:
                return (_jsxs(TextField, { label: "File 6", helperText: "Helper text 6", placeholder: "Placeholder 6", clearButtonVisible: true, children: [_jsx(Tooltip, { slot: "tooltip", text: "Tooltip text 6" }), _jsx(Icon, { slot: "prefix", icon: "vaadin:vaadin-h" }), _jsx("span", { slot: "suffix", children: ":)" })] }));
            default:
                return null;
        }
    }
    render() {
        const { renderTextField } = this.props;
        return (_jsx("div", { children: this.renderTextField(renderTextField) }));
    }
}
export default CustomTextFields;
//# sourceMappingURL=custom-textfields.js.map