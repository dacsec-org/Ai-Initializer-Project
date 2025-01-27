import { jsx as _jsx, jsxs as _jsxs } from "react/jsx-runtime";
import React, { Component } from 'react';
import { useSignal } from '@vaadin/hilla-react-signals';
import { MessageList } from '@vaadin/react-components/MessageList.js';
import MainMessageInput from './components/main-message-input';
import { ViewConfig } from '@vaadin/hilla-file-router';

export const config = {
  menu: { order: 6, icon: 'line-awesome/svg/comment-alt.svg' },
  title: 'Message ~ History',
};

class MainMessageListView extends Component {
  constructor(props) {
    super(props);
    this.items = useSignal([
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
  }

  handleSubmit = (e) => {
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

  render() {
    return (
      _jsxs(React.Fragment, {
        children: [
          _jsx(MessageList, { items: this.items.value }),
          _jsx(MainMessageInput, { onSubmit: this.handleSubmit })
        ]
      })
    );
  }
}

export default MainMessageListView;
