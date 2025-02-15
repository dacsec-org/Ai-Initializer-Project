import React, { useState, ChangeEvent } from 'react';
import './InputArea.scss';

class TextFieldValueChangedEvent {
  detail: any;
}

interface InputAreaProps {
  type?: 'text' | 'number' | 'password' | 'email',
  value?: string | number,
  placeholder?: string,
  className?: string,
  onChange?: (value: string | number) => void,
  label?: string,
  readonly?: boolean,
  style?: { width: string },
  onValueChanged?: (e: TextFieldValueChangedEvent) => void
}

const InputArea: React.FC<InputAreaProps> = ({
                                               type = 'text',
                                               value: initialValue = '',
                                               placeholder,
                                               className,
                                               onChange,
                                               label,
                                               readonly,
                                               style,
                                               onValueChanged
                                             }) => {
  const [value, setValue] = useState<string | number>(initialValue);

  const handleChange = (event: ChangeEvent<HTMLInputElement>) => {
    const newValue = event.target.value;
    setValue(newValue);

    // Call parent's `onChange` callback if provided
    if (onChange) {
      onChange(newValue);
    }
  };

  const inputClassName = `input-area ${className || ''}`;

  return (
    <div className={inputClassName}>
      {label && <label className="input-label">{label}</label>}
      <input
        type={type}
        value={value}
        placeholder={placeholder}
        onChange={handleChange}
        className="input-field"
      />
    </div>
  );
};

export default InputArea;
