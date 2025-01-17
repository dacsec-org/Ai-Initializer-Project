import '@vaadin/icons';
import React, { Component } from 'react';
import { Icon } from '@vaadin/react-components/Icon.js';
import { TextField } from '@vaadin/react-components/TextField.js';
import { Tooltip } from '@vaadin/react-components/Tooltip.js';

/**
 * {@link CustomTextFields}
 * <p>
 *   This is a custom component that renders multiple text fields.
 *   The text fields are created by calling the renderTextField function with a number as an argument.
 *   The renderTextField function returns a TextField component with different properties based on the argument.
 *   The properties include label, helperText, placeholder, clearButtonVisible, tooltip, prefix, and suffix.
 *   The component is then rendered with the text fields created by the renderTextField function.
 * </p>
 */
class CustomTextFields extends Component {
  renderTextField(fieldNumber: number) {
    switch (fieldNumber) {
      case 1:
        return (
          <TextField label="Model location" helperText="add custom LLM location(not recomended)"
                     placeholder="default: /home/USER/project-ai-initializer/models" clearButtonVisible>
            <Tooltip slot="tooltip" text="Tooltip text 1" />
            <Icon slot="prefix" icon="vaadin:vaadin-h" />
            <span slot="suffix">:)</span>
          </TextField>
        );
      case 2:
        return (
          <TextField label="Snapshots location" helperText="add custom LLM snapshot location(not recomended)"
                     placeholder="default: /home/USER/project-ai-initializer/models/.snapshots" clearButtonVisible>
            <Tooltip slot="tooltip" text="Tooltip text 2" />
            <Icon slot="prefix" icon="vaadin:vaadin-h" />
            <span slot="suffix">:)</span>
          </TextField>
        );
      case 3:
        return (
          <TextField label="path" helperText="enter path to directory"
                     placeholder="/path/to/directory" clearButtonVisible>
            <Tooltip slot="tooltip" text="Tooltip text 3" />
            <Icon slot="prefix" icon="vaadin:vaadin-h" />
            <span slot="suffix">:)</span>
          </TextField>
        );
      case 4:
        return (
          <TextField label="file path" helperText="enter path to file"
                     placeholder="/path/to/file.*" clearButtonVisible>
            <Tooltip slot="tooltip" text="Tooltip text 4" />
            <Icon slot="prefix" icon="vaadin:vaadin-h" />
            <span slot="suffix">:)</span>
          </TextField>
        );
      case 5:
        return (
          <TextField label="File 5" helperText="Helper text 5"
                     placeholder="Placeholder 5" clearButtonVisible>
            <Tooltip slot="tooltip" text="Tooltip text 5" />
            <Icon slot="prefix" icon="vaadin:vaadin-h" />
            <span slot="suffix">:)</span>
          </TextField>
        );
      case 6:
        return (
          <TextField label="File 6" helperText="Helper text 6"
                     placeholder="Placeholder 6" clearButtonVisible>
            <Tooltip slot="tooltip" text="Tooltip text 6" />
            <Icon slot="prefix" icon="vaadin:vaadin-h" />
            <span slot="suffix">:)</span>
          </TextField>
        );
      default:
        return null;
    }
  }

  /**
   * {@link render}
   * <p>
   *   This function renders the text fields created by the renderTextField function.
   * </p>
   */
  render() {
    return (
      <div>
        {this.renderTextField(1)}
        {this.renderTextField(2)}
        {this.renderTextField(3)}
        {this.renderTextField(4)}
        {this.renderTextField(5)}
        {this.renderTextField(6)}
      </div>
    );
  }
}

export default CustomTextFields;
