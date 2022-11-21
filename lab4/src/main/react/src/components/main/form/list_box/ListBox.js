import React from 'react';
import ListBoxItem from "./ListBoxItem";

function ListBox({ name, items }) {

    return (
        <select name={name} className="form__list-box">
            { items.map(item => {
                return <ListBoxItem key={item.id} value={item.value} />
            })}
        </select>
    )
}

export default ListBox