import React from 'react';
import Draggable from 'react-draggable';
import { ResizableBox } from 'react-resizable';
import './Dialog.scss';
import Button from './button';

interface DialogProps {
  isOpen: boolean,
  message: string,
  onClose: () => void,
  children?: React.ReactNode,
  opened?: boolean,
  onOpenedChanged?: (e) => void
}

/**
 * <h1>{@link Dialog}</h1>
 * @param isOpen
 * @param message
 * @param onClose
 * @param children
 * @constructor
 */
const Dialog: React.FC<DialogProps> = ({
                                         isOpen,
                                         message,
                                         onClose,
                                         children,
                                         opened,
                                         onOpenedChanged
                                       }) => {
  if (!isOpen) return null;

  return (
    <div className="dialog-overlay">
      <Draggable>
        <ResizableBox width={300} height={200} minConstraints={[150, 100]}
                      maxConstraints={[600, 400]}>
          <div className="dialog">
            <p>{message}</p>
            {children} {/* Render children */}
            <Button onClick={onClose} className="small-button">Close</Button>
          </div>
        </ResizableBox>
      </Draggable>
    </div>
  );
};

export default Dialog;
