import { createConnectionClient } from '../bridges/ConnectionFactory'
import { HelloWorldService } from '../bridges/endpoints';
import { NotificationService } from '../components/notifications';
import Button from '../components/button';
import InputArea from '../components/input-area';

export default function HelloWorldView() {
  const name = useSignal('');

  return (
    <>
      <InputArea
        label="Your name"
        onValueChanged={(e) => {
          name.value = e.detail.value;
        }}
      />
      <Button
        onClick={async () => {
          const serverResponse = await HelloWorldService.sayHello(name.value);
          NotificationService.show('Server response', 'error');
        }}>
        Say hello
      </Button>
    </>
  );
}

