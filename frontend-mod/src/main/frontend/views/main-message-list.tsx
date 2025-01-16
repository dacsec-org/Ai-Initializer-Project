import React, { useEffect } from 'react';
import { useSignal } from '@vaadin/hilla-react-signals';
import type { MessageListItem } from '@vaadin/message-list';
import { MessageInput } from '@vaadin/react-components/MessageInput.js';
import { MessageList } from '@vaadin/react-components/MessageList.js';
// @ts-ignore
import { getPeople } from 'Frontend/demo/domain/DataService';
import { ViewConfig } from '@vaadin/hilla-file-router/types.js';

export const config: ViewConfig = {
  menu: { order: 5, icon: 'line-awesome/svg/clone-solid.svg' },
  title: 'Clone Model',
};

function MainMessageList() {
  const items = useSignal<MessageListItem[]>([]);

  useEffect(() => {
    // @ts-ignore
    getPeople({ count: 1 }).then(({ people }) => {
      const person = people[0];
      items.value = [
        {
          text: 'Nature does not hurry, yet everything gets accomplished.',
          time: 'yesterday',
          userName: 'Matt Mambo',
          userColorIndex: 1,
        },
        {
          text: 'Using your talent, hobby or profession in a way that makes you contribute with something good to this world is truly the way to go.',
          time: 'right now',
          userName: 'Linsey Listy',
          userColorIndex: 2,
          userImg: person.pictureUrl,
        },
      ];
    });
  }, []);

  return (
    <>
      <MessageList items={items.value} />
      <MessageInput
        onSubmit={(e) => {
          items.value = [
            ...items.value,
            {
              text: e.detail.value,
              time: 'seconds ago',
              userName: 'Milla Sting',
              userAbbr: 'MS',
              userColorIndex: 3,
            },
          ];
        }}
      />
    </>
  );
}

