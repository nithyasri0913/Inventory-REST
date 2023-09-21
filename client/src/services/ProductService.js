import axios from "axios";
const PRODUCTS_REST_API_URL = "http://localhost:8085/ims/api/products"


// service class to manager PRODUCTS REST API
class ProductService {
    static getProducts() {
        try {
            return axios.get(PRODUCTS_REST_API_URL);
        }
        catch (error) {
            console.error("Error getting products")
        }
    }

    static createProduct(product) {
        return axios.post(PRODUCTS_REST_API_URL, product);
    }

    static getProductById(productId) {
        return axios.get(PRODUCTS_REST_API_URL + '/' + productId);
    }
    static updateProduct(product, productId) {
        return axios.put(PRODUCTS_REST_API_URL + '/' + productId, product);
    }

    static deleteProduct(productId) {
        return axios.delete(PRODUCTS_REST_API_URL + '/' + productId);
    }

    static async searchProductByName(name) {
        try {
            const response = await axios.get(`${PRODUCTS_REST_API_URL}/search?name=${name}`);
            // console.log(response)
            return response;
        }
        catch (error) {
            console.error("Error searching for product", error);
        }
    }
}

export default ProductService;