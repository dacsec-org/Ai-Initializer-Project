import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { useSignal } from '@vaadin/hilla-react-signals';
import { Button, Notification, TextField, Dialog } from '@vaadin/react-components';
import { DeleteModelService } from 'Frontend/generated/endpoints.js';

export const config: ViewConfig = {
  menu: { order: 10, icon: 'line-awesome/svg/trash-alt-solid.svg' },
  title: 'Delete Model',
};

export default function DeleteModelView() {
  const modelPath = useSignal('');
  const dialogOpened = useSignal(false);

  const handleDelete = async () => {
    const response = await DeleteModelService.deleteModel(modelPath.value);
    Notification.show(response);
    dialogOpened.value = false;
  };

  const openDialog = () => {
    dialogOpened.value = true;
  };

  const closeDialog = () => {
    dialogOpened.value = false;
  };

  return (
    <>
      <section className="flex p-m gap-m items-end">
        <TextField
          label="Model Path"
          value={modelPath.value}
          onValueChanged={(e) => {
            modelPath.value = e.detail.value;
          }}
        />
        <Button onClick={openDialog}>
          Delete Model
        </Button>
      </section>
      <Dialog opened={dialogOpened.value} onOpenedChanged={(e) => dialogOpened.value = e.detail.value}>
        <div>
          <p>Are you sure you want to delete this model?
            Any work associated with this model will be deleted as well!</p>
          <div className="flex gap-s">
            <Button theme="primary error" onClick={handleDelete}>
              Yes
            </Button>
            <Button theme="secondary" onClick={closeDialog}>
              No
            </Button>
          </div>
        </div>
      </Dialog>
    </>
  );
}
