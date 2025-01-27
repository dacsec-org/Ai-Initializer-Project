import React, { Component } from 'react';
import { useSignal } from '@vaadin/hilla-react-signals';
import type { MessageListItem } from '@vaadin/message-list';
import MainMessageInput from './components/main-message-input';
import { MessageList } from '@vaadin/react-components/MessageList.js';
import { ViewConfig } from '@vaadin/hilla-file-router/types.js';

export const config: ViewConfig = {
  menu: { order: 6, icon: 'line-awesome/svg/comment-alt.svg' },
  title: 'Message ~ History',
};

/**
 * <h1>{@link MainMessageListView}</h1>
 */
class MainMessageListView extends Component {
  items = useSignal<MessageListItem[]>([
    {
      text: 'This is a stub message.',
      time: 'yesterday',
      userName: 'Matt Mambo',
      userColorIndex: 1,
    },
    {
      text: 'Using your talent, hobby or profession in a way that makes you contribute with something good to this world is truly the way to go.',
      time: 'right now',
      userName: 'Linsey Listy',
      userColorIndex: 2,
    },
  ]);

  /**
   * {@link #handleSubmit} is called when the user submits a new message.
   * @param e
   */
  handleSubmit = (e: CustomEvent) => {
    this.items.value = [
      ...this.items.value,
      {
        text: e.detail.value,
        time: 'seconds ago',
        userName: 'Milla Sting',
        userAbbr: 'MS',
        userColorIndex: 3,
      },
    ];
  };

  /**
   * {@link #render}
   */
  render() {
    return (
      <>
        <MessageList items={this.items.value} />
        <MainMessageInput onSubmit={this.handleSubmit} />
      </>
    );
  }
}

export default MainMessageListView;
