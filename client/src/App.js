import logo from './logo.svg';
import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import NavBar from "./components/NavBar.js"
import Registration from './components/Registration';
import Login from './components/Login';
import { library } from '@fortawesome/fontawesome-svg-core';
import { faSign, faCameraRetro, faCoffee, faBomb, faTrash, faEdit, faList, faPeopleGroup, faSearch } from '@fortawesome/free-solid-svg-icons';
import Products from './components/Products';
import CreateProduct from './components/CreateProduct';
import ViewProduct from './components/ViewProduct';
import DealersInfo from './components/DealersInfo';
import ProductSearch from './components/ProductSearch';
import About from './components/About';
library.add(faSign, faCameraRetro, faCoffee, faBomb, faTrash, faEdit, faList, faPeopleGroup, faSearch);
/*
  React Router is a standard library for routing in React. 
  It enables the navigation among views of various components in a React Application, 
  allows changing the browser URL, and keeps the UI in sync with the URL. 

  React Router is a JavaScript framework that lets us handle client and server-side 
  routing in React applications. 
  It enables the creation of single-page web or mobile apps that allow navigating without 
  refreshing the page. 
  It also allows us to use browser history features while preserving the right application
   view.

   Used Version6 of Router

 > npm install react-router-dom --save
*/

function App() {
  return (

    <div className="App">
      <header className="App-header">
        <h1> Loan Management System </h1>
        <h3> GIS Global Mart </h3>
      </header>
      <section>
        <div style={{
          backgroundImage: "url(/images/loan-bg.jpg)",
          backgroundSize: 'cover', backgroundRepeat: "no-repeat",
          minHeight: '140vh', minWidth: '100vw'
        }}>
          <Router>
            <NavBar />
            <Routes>
              <Route path="/about" element={<About />} />
              <Route path="/register" element={<Registration />} />
              <Route path="/login" element={<Login />} />
              <Route path="/products" element={<Products />} />
              <Route path="/dealers" element={<DealersInfo />} />
              <Route path="/addProducts/:id" element={<CreateProduct />} />
              <Route path="/viewProduct/:id" element={<ViewProduct />} />
              <Route path='/search' element={<ProductSearch />} />
            </Routes>
          </Router>

        </div>
      </section>
      <footer className="footer">
        <p><strong>&copy; All rights reserved </strong> </p>
      </footer>
    </div>

  );
}

export default App;
