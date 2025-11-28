import { useState } from "react";
import "./MultiTagInput.css";

export default function MultiTagInput({ label, value, onChange, suggestions }) {
    const [inputValue, setInputValue] = useState("");

    const addTag = (tag) => {
        if (tag && !value.includes(tag)) {
            onChange([...value, tag]);
        }
        setInputValue("");
    };

    const removeTag = (tag) => {
        onChange(value.filter(t => t !== tag));
    };

    const handleKeyDown = (e) => {
        if (e.key === "Enter" || e.key === ",") {
            e.preventDefault();
            addTag(inputValue.trim());
        }
    };

    return (
        <div className="multi-tag-container">
            <label>{label}</label>

            <div className="tags-zone">
                {value.map((tag, idx) => (
                    <span key={idx} className="tag-chip">
                        {tag}
                        <button type="button" onClick={() => removeTag(tag)}>×</button>
                    </span>
                ))}

                <input
                    className="tag-input"
                    value={inputValue}
                    onChange={(e) => setInputValue(e.target.value)}
                    onKeyDown={handleKeyDown}
                    placeholder="Ajouter..."
                />
            </div>

            {/** Suggestions */}
            {inputValue.length > 0 && (
                <div className="suggestions">
                    {suggestions
                        .filter(s => s.toLowerCase().includes(inputValue.toLowerCase()))
                        .slice(0, 5)
                        .map((s, i) => (
                            <div
                                key={i}
                                className="suggestion-item"
                                onClick={() => addTag(s)}
                            >
                                {s}
                            </div>
                        ))}
                </div>
            )}
        </div>
    );
}