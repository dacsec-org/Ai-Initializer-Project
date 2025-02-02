import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { Button, Notification, TextField } from '@vaadin/react-components';
import { HelloWorldService } from 'Frontend/generated/endpoints';
import { useSignal } from '@vaadin/hilla-react-signals';

export const config: ViewConfig = {
  menu: { order: 10, icon: 'line-awesome/svg/smile-solid.svg' },
  title: 'Hello World'
};

export default function MainView() {
  const name = useSignal('');

  return (
    <>
      <TextField
        label="Your name"
        onValueChanged={(e) => {
          name.value = e.detail.value;
        }}
      />
      <Button
        onClick={async () => {
          const serverResponse = await HelloWorldService.sayHello(name.value);
          Notification.show(serverResponse);
        }}>
        Say hello
      </Button>
    </>
  );
}

