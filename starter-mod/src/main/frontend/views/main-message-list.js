import { jsx as _jsx, Fragment as _Fragment, jsxs as _jsxs } from "react/jsx-runtime";
import React, { Component } from 'react';
import { useSignal } from '@vaadin/hilla-react-signals';
import { MessageList } from '@vaadin/react-components/MessageList.js';
// @ts-ignore
import { getPeople } from 'Frontend/demo/domain/DataService';
export const config = {
    menu: { order: 6, icon: 'line-awesome/svg/comment-alt.svg' },
    title: 'Message History',
};
class MainMessageList extends Component {
    constructor() {
        super(...arguments);
        this.items = useSignal([]);
        this.handleSubmit = (e) => {
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
    }
    componentDidMount() {
        // @ts-ignore
        getPeople({ count: 1 }).then(({ people }) => {
            const person = people[0];
            this.items.value = [
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
    }
    render() {
        return (_jsxs(_Fragment, { children: [_jsx(MessageList, { items: this.items.value }), _jsx(MessageInput, { onSubmit: this.handleSubmit })] }));
    }
}
export default MainMessageList;
//# sourceMappingURL=main-message-list.js.map