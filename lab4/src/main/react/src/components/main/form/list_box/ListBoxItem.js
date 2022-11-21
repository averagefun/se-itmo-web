import React from 'react';

function ListBoxItem({ value }) {
    return (
        <option className="form__list-box-item" value={value}>{value}</option>
    )
}

export default ListBoxItem