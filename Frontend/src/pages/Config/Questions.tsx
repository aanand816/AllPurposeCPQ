import ConfigLayout from "./ConfigLayout";
import "./Config.css";

function Questions() {
    return (
        <ConfigLayout title="Define Product Questions" activeSide="app-settings" activeTab="questions">
            <section className="config-card config-question-card">
                <div className="config-question-grid">
                    <label className="config-field config-question-field" data-area="question">
                        <span>Question:</span>
                        <input type="text" defaultValue="Gold" />
                    </label>
                    <label className="config-field config-question-field" data-area="description">
                        <span>Description:</span>
                        <textarea
                            rows={6}
                            defaultValue="This is not paint, it is Gold flake and may wrinkle if left in the sun for prolonged time."
                        />
                    </label>
                    <label className="config-field config-question-field" data-area="type">
                        <span>Question Type:</span>
                        <select defaultValue="radio">
                            <option value="radio">Radio</option>
                            <option value="select">Select</option>
                            <option value="number">Number</option>
                            <option value="text">Text</option>
                        </select>
                    </label>
                    <label className="config-field config-question-field" data-area="page">
                        <span>Page Number:</span>
                        <input type="number" min={1} defaultValue={1} />
                    </label>
                    <label className="config-field config-question-field" data-area="image">
                        <span>Image:</span>
                        <input type="file" />
                    </label>
                </div>
            </section>

            <div className="config-actions">
                <button className="config-action config-action-ghost">Back</button>
                <button className="config-action config-action-primary">Next</button>
                <button className="config-action config-action-danger">Cancel</button>
            </div>
        </ConfigLayout>
    );
}

export default Questions;
