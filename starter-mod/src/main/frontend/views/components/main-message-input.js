import { jsx as _jsx } from "react/jsx-runtime";
import React, { Component } from 'react';
import { MessageInput } from '@vaadin/react-components/MessageInput.js';
/**
 * {@link MainMessageInput}
 * <p>
 *   This is the main message input component that renders a message input field
 *   to be used in chats.
 * </p>
 */
class MainMessageInput extends Component {
    render() {
        return _jsx(MessageInput, { onSubmit: this.props.onSubmit });
    }
}
export default MainMessageInput;
//# sourceMappingURL=main-message-input.js.map