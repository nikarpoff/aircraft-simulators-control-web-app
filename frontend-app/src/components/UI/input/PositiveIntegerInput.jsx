import React, { useState } from 'react';

export default function PositiveIntegerInput({text}) {
  const [value, setValue] = useState('');

  const handleChange = (e) => {
    const inputValue = e.target.value;
    // Проверяем, является ли введенное значение положительным целым числом
    if (/^\d*$/.test(inputValue)) {
      setValue(inputValue);
    }
  };

  return (
    <input
      type="text"
      value={value}
      onChange={handleChange}
      placeholder={text}
    />
  );
}