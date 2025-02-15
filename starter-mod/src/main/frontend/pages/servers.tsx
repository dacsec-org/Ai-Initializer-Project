import React, { useState } from 'react';
// import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
// import { Button, Dialog, Notification } from '@vaadin/react-components';
// @ts-ignore
import { ServersService } from 'Frontend/generated/endpoints';

export const config: ViewConfig = {
  menu: { order: 13, icon: 'line-awesome/svg/server-solid.svg' , title: 'Servers' }};

const ManageServersView: React.FC = () => {
  const [dialogOpened, setDialogOpened] = useState(false);
  const [dialogMessage, setDialogMessage] = useState('');
  const [dialogAction, setDialogAction] = useState<() => void>(() => {});

  const openDialog = (message: string, action: () => void) => {
    setDialogMessage(message);
    setDialogAction(() => action);
    setDialogOpened(true);
  };

  const handleDialogClose = () => {
    setDialogOpened(false);
  };

  const startServer = async () => {
    const result = await ServersService.manageServer('start');
    Notification.show("Started server" + result);
  };

  const stopServer = async () => {
    const result = await ServersService.manageServer('stop');
    Notification.show("Server stopped" + result);
  };

  const restartServer = async () => {
    const result = await ServersService.manageServer('restart');
    Notification.show("Restarted server" + result);
  };

  return (
    <>
      <section className="flex p-m gap-m items-end">
        <Button
          onClick={() => openDialog('Are you sure you want to start the server?', startServer)}
          style={{ backgroundColor: 'green' }}>Start Server</Button>
        <Button
          onClick={() => openDialog('Are you sure you want to stop the server?', stopServer)}
          style={{ backgroundColor: 'red' }}>Stop Server</Button>
        <Button
          onClick={() => openDialog('Are you sure you want to restart the server?', restartServer)}
          style={{ backgroundColor: 'blue' }}>Restart Server</Button>
      </section>
      <Dialog opened={dialogOpened}
              onOpenedChanged={(e) => setDialogOpened(e.detail.value)}>
        <div>
          <p>{dialogMessage}</p>
          <div className="flex gap-s">
            <Button theme="primary" onClick={() => {
              dialogAction();
              handleDialogClose();
            }}>
              Yes
            </Button>
            <Button theme="secondary" onClick={handleDialogClose}>
              No
            </Button>
          </div>
        </div>
      </Dialog>
    </>
  );
};

export default ManageServersView;
