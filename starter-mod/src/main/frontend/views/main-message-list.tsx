// import React, { Component } from 'react';
// import { useSignal } from '@vaadin/hilla-react-signals';
// import type { MessageListItem } from '@vaadin/message-list';
// import MainMessageInput from './components/main-message-input';
// import { MessageList } from '@vaadin/react-components/MessageList.js';
//
// // @ts-ignore
// import { getPeople } from 'Frontend/demo/domain/DataService';
// import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
//
// export const config: ViewConfig = {
//   menu: { order: 6, icon: 'line-awesome/svg/comment-alt.svg' },
//   title: 'Message History',
// };
//
// class MainMessageList extends Component {
//   items = useSignal<MessageListItem[]>([]);
//
//   componentDidMount() {
//     // @ts-ignore
//     getPeople({ count: 1 }).then(({ people }) => {
//       const person = people[0];
//       this.items.value = [
//         {
//           text: 'Nature does not hurry, yet everything gets accomplished.',
//           time: 'yesterday',
//           userName: 'Matt Mambo',
//           userColorIndex: 1,
//         },
//         {
//           text: 'Using your talent, hobby or profession in a way that makes you contribute with something good to this world is truly the way to go.',
//           time: 'right now',
//           userName: 'Linsey Listy',
//           userColorIndex: 2,
//           userImg: person.pictureUrl,
//         },
//       ];
//     });
//   }
//
//   handleSubmit = (e: CustomEvent) => {
//     this.items.value = [
//       ...this.items.value,
//       {
//         text: e.detail.value,
//         time: 'seconds ago',
//         userName: 'Milla Sting',
//         userAbbr: 'MS',
//         userColorIndex: 3,
//       },
//     ];
//   };
//
//   render() {
//     return (
//       <>
//         <MessageList items={this.items.value} />
//         <MainMessageInput onSubmit={this.handleSubmit} />
//       </>
//     );
//   }
// }
//
// export default MainMessageList;
