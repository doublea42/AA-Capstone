import axios from "axios";
import BindingClass from "../util/bindingClass";
import Authenticator from "./authenticator";

export default class PawDorableClient extends BindingClass{
    constructor(props = {}){
        super();
        
        const methodsToBind = ['clientLoaded', 'getIdentity', 'login', 'logout', 'getAllPets',
                                'handleError', 'getTokenOrThrow', 'isLoggedIn', 'getProfile',
                                'getPet', 'getRentalHistory', 'getActiveRental', 'CreateProfile',
                                 'CreatePet', 'CreateActiveRental', 'UpdateProfile', 'UpdatePet',
                                  'RemovePet', 'RemoveActiveRental']; 
        this.bindClassMethods(methodsToBind, this);

        this.authenticator = new Authenticator();
        this.props = props;

        axios.defaults.baseURL = process.env.API_BASE_URL;
        this.axiosClient = axios;
        this.clientLoaded();
    }


    /**
     * Run any functions that are supposed to be called once the client has loaded successfully.
     */
    clientLoaded() {
        if (this.props.hasOwnProperty("onReady")) {
            this.props.onReady(this);
        }
    }

    async getIdentity(errorCallback) {
        
        try {
            const isLoggedIn = await this.authenticator.isUserLoggedIn();

            if (!isLoggedIn) {
                return undefined;
            }
            return await this.authenticator.getCurrentUserInfo();
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async login() {
        this.authenticator.login();
    }

    async logout() {
        this.authenticator.logout();
    }

    async isLoggedIn(){
        return this.authenticator.isUserLoggedIn();
    }


    // /**
    //  * Get the identity of the current user
    //  * @param errorCallback (Optional) A function to execute if the call fails.
    //  * @returns The user information for the current user.
    //  */
    // async getIdentity(errorCallback) {
    //     try {
    //         const isLoggedIn = await this.authenticator.isUserLoggedIn();

    //         if (!isLoggedIn) {
    //             return undefined;
    //         }

    //         return await this.authenticator.getCurrentUserInfo();
    //     } catch (error) {
    //         this.handleError(error, errorCallback)
    //     }
    // }

    async getTokenOrThrow(unauthenticatedErrorMessage) {
        const isLoggedIn = await this.authenticator.isUserLoggedIn();
        if (!isLoggedIn) {
            throw new Error(unauthenticatedErrorMessage);
        }

        return await this.authenticator.getUserToken();
    }

    /**
     * Helper method to log the error and run any error functions.
     * @param error The error received from the server.
     * @param errorCallback (Optional) A function to execute if the call fails.
     */
    handleError(error, errorCallback) {
        console.error(error);

        const errorFromApi = error?.response?.data?.error_message;
        if (errorFromApi) {
            console.error(errorFromApi)
            error.message = errorFromApi;
        }

        if (errorCallback) {
            errorCallback(error);
        }
    }

    async getAllPets(errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can see pets.");
            const response = await this.axiosClient.get(`pet/All`,
            {
                 headers: {
                Authorization: `Bearer ${token}`,
                }
            }
            );
            console.log(response);
            return response.data.listOfPets;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async getProfile(email, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can see their profiles.");
            const response = await this.axiosClient.get(`profile/${email}`,
            {
                 headers: {
                Authorization: `Bearer ${token}`}
            }
            );
            console.log(response);
            return response.data.profile;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async getPet(petID, errorCallback) {
        try {

            const token = await this.getTokenOrThrow("Only authenticated users can see pets.");
            const response = await this.axiosClient.get(`pet/`,
             {
                id: petID,
            },
             {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            },
            );

            console.log(response);
            return response.data.pet;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async getRentalHistory(id, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can see pets.");
            const response = await this.axiosClient.get(`history/`,
            {historyID: id},
            {
                 headers: {
                Authorization: `Bearer ${token}`}
            }
            );
            console.log(response);
            return response.data.rentalHistoryModel;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async getActiveRental(id, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can see pets.");
            const response = await this.axiosClient.get(`active/`,
            {rentalID: id},
            {
                 headers: {
                Authorization: `Bearer ${token}`}
            }
            );
            console.log(response);
            return response.data.activeRental;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async CreateProfile(first, last, age, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can see pets.");
            const response = await this.axiosClient.post(`profile/create/`,
            {firstName: first, lastName: last, age: age},
            {
                 headers: {
                Authorization: `Bearer ${token}`}
            }
            );
            console.log(response);
            return response.data.profile;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async CreatePet(petsName, kind, age, gender, rentable, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can see pets.");
            const response = await this.axiosClient.post(`pet/create`,
            {name: petsName, kindOfPet: kind, age: age, gender: gender, available: rentable},
            {
                 headers: {
                Authorization: `Bearer ${token}`}
            }
            );
            console.log(response);
            return response.data.pet;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async CreateActiveRental(petsID, email, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can rent pets.");
            const response = await this.axiosClient.post(`Active/create`,
            {petID: petsID, profileID: email},
            {
                 headers: {
                Authorization: `Bearer ${token}`}
            }
            );
            console.log(response);
            return response.data.activeRental;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async UpdateProfile(emailAddress, firstName, lastName, age, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can see pets.");
            const response = await this.axiosClient.put(`profile/update/${emailAddress}`,
            {firstName: firstName, lastName: lastName, age: age},
            {
                 headers: {
                Authorization: `Bearer ${token}`}
            }
            );
            console.log(response);
            return response.data.profile;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async UpdatePet(petsID, rent, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can see pets.");
            const response = await this.axiosClient.put(`pet/update/`,
            {id: petsID, available: rent},
            {
                 headers: {
                Authorization: `Bearer ${token}`}
            }
            );
            console.log(response);
            return response.data.pet;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async RemovePet(petsID, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can see pets.");
            const response = await this.axiosClient.delete(`pet/id/delete/`,
            {id: petsID},
            {
                 headers: {
                Authorization: `Bearer ${token}`}
            }
            );
            console.log(response);
            return response.data.remove;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }


    async RemoveActiveRental(petsID, score, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can see pets.");
            const response = await this.axiosClient.delete(`active/id/delete/`,
            {petID: petsID, rentalScore: score},
            {
                 headers: {
                Authorization: `Bearer ${token}`}
            }
            );
            console.log(response);
            return response.data.remove;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }










}
