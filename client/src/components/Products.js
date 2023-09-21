import React, { useState, useEffect } from 'react'
import { Navigate } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import ProductService from '../services/ProductService';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

function Products() {

  const history = useNavigate();
  const [products, setProducts] = useState([]);
  const [message, setMessage] = useState('');

  /*
     The useEffect hook in React is use to handle the side effects in React such as 
     fetching data, and updating DOM. This hook runs on every render but there is 
     also a way of using a dependency array using which we can control the effect of 
     rendering.
 
     The motivation behind the introduction of useEffect Hook is to eliminate the 
     side effects of using class-based components.
 
     Syntax: useEffect(<FUNCTION>, <DEPENDECY>)
      - To run useEffect on every render do not pass any dependency
      - To run useEffect only once on the first render pass any empty array in the dependecy
      - To run useEffect on change of a particular value. Pass the state and props in the dependency array
      */

  useEffect(() => {
    fetchProducts();
  }, []);

  const fetchProducts = () => {
    try {
      ProductService.getProducts().then((response) => {
        setProducts(response.data);
      })
    }
    catch (error) {
      console.error("Fetch error");
    }
  };

  const addProduct = () => {
    history('/addProducts/_add')
  }

  const editProduct = (id) => {
    history(`/addProducts/${id}`)
  };

  const deleteProduct = (id) => {
    ProductService.deleteProduct(id).then(() => {
      fetchProducts();
      setMessage('Product Deleted Successfully!');
    });
    setTimeout(() => {
      setMessage('');
    }, 2000);
  };

  const viewProduct = (id) => {
    history(`/viewProduct/${id}`);
  };

  return (
    <div>
      <br />

      <h1 className="text-warning">Products List</h1>
      <br />
      <div className='row justify-content-center'>
        <button className='btn btn-info w-auto' onClick={addProduct}> Add Product </button>
      </div>
      <br />
      <div className="row justify-content-center">

      </div>
      <br />
      <div className="row justify-content-center" >
        <table className="table table-success w-auto">
          <thead>
            <tr className="table-danger">
              <th> Product Id</th>
              <th> Product Name</th>
              <th> Brand</th>
              <th> MadeIn</th>
              <th> Price</th>
              <th> Actions</th>
            </tr>
          </thead>
          <tbody>
            { /*
    We are using the map operator to loop over our products list and create the view
    */}
            {products.map(
              prod =>
                <tr key={prod.pid}>
                  <td> {prod.pid} </td>
                  <td> {prod.name} </td>
                  <td> {prod.brand} </td>
                  <td> {prod.madeIn} </td>
                  <td> {prod.price} </td>
                  <td>
                    <button className='btn btn-success' onClick={() => editProduct(prod.pid)}>
                      <span> <FontAwesomeIcon icon="edit"></FontAwesomeIcon></span>
                    </button> &nbsp;
                    <button className='btn btn-danger' onClick={() => deleteProduct(prod.pid)}>
                      <span> <FontAwesomeIcon icon="trash"></FontAwesomeIcon></span>
                    </button>&nbsp;
                    <button className='btn btn-primary' onClick={() => viewProduct(prod.pid)}>
                      <span> <FontAwesomeIcon icon="list"></FontAwesomeIcon></span>
                    </button>&nbsp;
                  </td>

                </tr>
            )
            }
          </tbody>
        </table>
      </div>
      {message && <div className='alert alert-success'>{message}</div>}
    </div>
  )
}

export default Products