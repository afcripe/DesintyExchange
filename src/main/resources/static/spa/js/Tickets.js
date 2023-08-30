import AbstractView from "./AbstractView.js";

export default class extends AbstractView {
    constructor(params) {
        super(params);
        this.setTitle("Tickets");
    }

    async getHtml() {
        return `
            <h1>Tickets</h1>
            <p>Lorem Ispum.</p>
        `;
    }
}