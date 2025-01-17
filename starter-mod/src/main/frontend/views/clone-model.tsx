import React, { Component } from 'react';
import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { Button, Notification, TextField } from '@vaadin/react-components';
import { CloneLocalModelService } from 'Frontend/generated/endpoints.js';

export const config: ViewConfig = {
  menu: { order: 2, icon: 'line-awesome/svg/clone-solid.svg' },
  title: 'Clone Model',
};

interface CloneModelViewProps {}

interface CloneModelViewState {
  sourcePath: string;
  snapshotPath: string;
}

class CloneModelView extends Component<CloneModelViewProps, CloneModelViewState> {
  constructor(props: CloneModelViewProps) {
    super(props);
    this.state = {
      sourcePath: '',
      snapshotPath: ''
    };
  }

  handleClone = async () => {
    const { sourcePath, snapshotPath } = this.state;
    const response = await CloneLocalModelService.cloneModel(sourcePath, snapshotPath);
    Notification.show(response);
  };

  handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    this.setState({ [e.target.name]: e.target.value } as Partial<CloneModelViewState>);
  };

  render() {
    const { sourcePath, snapshotPath } = this.state;

    return (
      <>
        <section className="flex p-m gap-m items-end">
          <TextField
            label="Source Path"
            name="sourcePath"
            value={sourcePath}
            onValueChanged={(e) => this.handleInputChange(e)}
          />
          <TextField
            label="Snapshot Path"
            name="snapshotPath"
            value={snapshotPath}
            onValueChanged={(e) => this.handleInputChange(e)}
          />
          <Button onClick={this.handleClone}>
            Clone Model
          </Button>
        </section>
      </>
    );
  }
}

export default CloneModelView;
