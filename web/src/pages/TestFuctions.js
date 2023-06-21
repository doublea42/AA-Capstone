import axios from "axios";
import BindingClass from "../util/bindingClass";
import Authenticator from "./authenticator";

export default class Test extends BindingClass{

    constructor(props = {}){
        super();

        this.bindClassMethods(['mount'], this)

        this.client = new PawDorableClient();



    }

    mount(){}

}



const main = async () => {
    const test = new Test();
    test.mount();
};

window.addEventListener('DOMContentLoaded', main);