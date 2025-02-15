import React, { useState } from 'react';
import './MessageInputBar.scss';
import InputArea from './input-area';
import Button from './button';

interface MessageInputBarProps {
    onSend?: (message: string) => void; // Callback when the send button is clicked
    placeholder?: string; // Placeholder text
    className?: string; // Optional additional CSS classes
}

/**
 * <h1>{@link MessageInputBar}</h1>
 * is a React functional component that provides a text input bar for composing
 * and sending messages. It includes an input field and a send button, enabling users
 * to input and submit messages.
 *
 * @typedef {Object} MessageInputBarProps
 * @property {Function} onSend - A callback function that is triggered when the send button is clicked.
 *                               Receives the current input message as an argument.
 * @property {string} [placeholder='Enter your message...'] - Placeholder text displayed in the input field.
 * @property {string} [className] - Optional CSS class name to style the component.
 */
const MessageInputBar: React.FC<MessageInputBarProps> = ({ onSend, placeholder = 'Enter your message...', className }) => {
    const [message, setMessage] = useState<string>(''); // State to manage the current message

    const handleInputChange = (newValue: string | number) => {
        setMessage(String(newValue)); // Convert numbers to string
    };

    const handleSend = () => {
        if (message.trim()) {
            if (onSend) {
                onSend(message); // Trigger `onSend` callback
            }
            setMessage(''); // Clear the input
        }
    };

    const barClassName = `message-input-bar ${className || ''}`;

    return (
        <div className={barClassName}>
            <InputArea
                type="text"
                value={message}
                placeholder={placeholder}
                onChange={handleInputChange}
                className="message-input"
            />
            <Button className="send-button" onClick={handleSend}>
                Send
            </Button>
        </div>
    );
};

export default MessageInputBar;