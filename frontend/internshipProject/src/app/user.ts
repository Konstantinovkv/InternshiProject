export class User {
  id: number;
  username: string;
  email: string;
  password: string;
  address: Address;

  constructor(id: number, username: string, email: string, password: string, address: Address) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
    this.address = address;
  }
}

export class Address {
  country: string;
  city: string;
  street: string;

  constructor(country: string, city: string, street: string) {
    this.country = country;
    this.city = city;
    this.street = street;
  }
}

