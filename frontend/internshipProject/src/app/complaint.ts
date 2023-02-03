export class Complaint {
  date: Date;
  text: string;
  topic: string;

  constructor(date: Date, text: string, topic: string) {
    this.date = date;
    this.text = text;
    this.topic = topic;
  }
}

