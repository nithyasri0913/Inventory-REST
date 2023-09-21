import React, { useState, useEfect } from 'react';
import { useNavigate } from "react-router-dom";
import ProductService from '../services/ProductService';
import "../styles/ProductSearch.css"

const ProductSearch = () => {
    const history = useNavigate();
    const [searchTerm, setSearchTerm] = useState('');
    const [products, setProducts] = useState([]);
    const [message, setMessage] = useState();

    const handleSearch = async () => {
        try {
            const data = await ProductService.searchProductByName(searchTerm);
            if (data.length == 0) {
                setMessage("No product found");
                setProducts([])
            }
            else if (data.status == 404) {
                setMessage("No product found");
                setProducts([])
            } else {
                setMessage("");
                setProducts(data.data);
            }
        }
        catch (error) {
            console.error("Error searching for product: ", error)
        }
    }

    return (
        <div>
            <br />
            <div className="product-search-container">
                <h2>Product Search</h2>
                <div className="search-bar">
                    <input
                        type="text"
                        placeholder="Enter product name"
                        value={searchTerm}
                        onChange={(e) => setSearchTerm(e.target.value)}
                    />
                    <button onClick={handleSearch}>Search</button>
                </div>
                {message && <p className="message">{message}</p>}
                {products.length > 0 && (
                    <table className="product-table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Brand</th>
                                <th>Made In</th>
                                <th>Price</th>
                            </tr>
                        </thead>
                        <tbody>
                            {products.map((product) => (
                                <tr key={product.id}>
                                    <td>{product.pid}</td>
                                    <td>{product.name}</td>
                                    <td>{product.brand}</td>
                                    <td>{product.madeIn}</td>
                                    <td>{product.price}</td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                )}
            </div></div>
    )
}

export default ProductSearch