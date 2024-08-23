class MessagesModel {
    id?: number;
    userEmail?: string;
    title: string;
    question: string;
    adminEmail?: string;
    response?: string;
    closed?: boolean;

    constructor(title: string, question: string) {
        this.title = title;
        this.question = question;
    }
}

export default MessagesModel;