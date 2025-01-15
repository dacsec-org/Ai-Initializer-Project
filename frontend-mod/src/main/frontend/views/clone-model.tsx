import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { useSignal } from '@vaadin/hilla-react-signals';
import { Button, Notification, TextField } from '@vaadin/react-components';
import { CloneLocalModelService } from 'Frontend/generated/endpoints.js';

export const config: ViewConfig = {
  menu: { order: 9, icon: 'line-awesome/svg/clone-solid.svg' },
  title: 'Clone Model',
};

export default function CloneModelView() {
  const sourcePath = useSignal('');
  const snapshotPath = useSignal('');

  const handleClone = async () => {
    const response = await CloneLocalModelService.cloneModel(sourcePath.value, snapshotPath.value);
    Notification.show(response);
  };

  return (
    <>
      <section className="flex p-m gap-m items-end">
        <TextField
          label="Source Path"
          value={sourcePath.value}
          onValueChanged={(e) => {
            sourcePath.value = e.detail.value;
          }}
        />
        <TextField
          label="Snapshot Path"
          value={snapshotPath.value}
          onValueChanged={(e) => {
            snapshotPath.value = e.detail.value;
          }}
        />
        <Button onClick={handleClone}>
          Clone Model
        </Button>
      </section>
    </>
  );
}
