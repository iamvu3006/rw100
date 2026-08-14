import {myName} from "./Person";
import v_Person from "./Person";

console.log(`myName: ${myName}`);

var person1= new v_Person();
person1.id=1;
person1.name="Nguyen Van A";
person1.address="Ha Noi";

var person2= new v_Person();
person2.id=2;
person2.name="Nguyen Van B";
person2.address="Hai Phong";

console.log(`Person 1, id: ${person1.id}, name: ${person1.name}, address: ${person1.address}`);
console.log(`Person 2, id: ${person2.id}, name: ${person2.name}, address: ${person2.address}`);


