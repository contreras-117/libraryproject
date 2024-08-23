class MessagesModel {
    private id?: number;
    private userEmail?: string;
    private title: string;
    private question: string;
    private adminEmail?: string;
    private response?: string;
    private closed?: boolean;

    constructor(title: string, question: string) {
        this.title = title;
        this.question = question;
    }
}

export default MessagesModel;