import React from "react";
import { Link } from "react-router-dom";
import "../styles/Navbar.css"
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";


const NavBar = () => {
    return (
        <nav className="navbar">
            <ul className="nav-list">
                <li className="nav-item">
                    <Link to="/products" className="nav-link">
                        <span> <FontAwesomeIcon icon={"bomb"}> </FontAwesomeIcon></span>&nbsp;
                        Products
                    </Link>
                </li>
                <li className="nav-item">
                    <Link to="/products" className="nav-link">
                        <span> <FontAwesomeIcon icon={"bomb"}> </FontAwesomeIcon></span>&nbsp;
                        Products
                    </Link>
                </li>
                <li className="nav-item">
                    <Link to="/search" className="nav-link">
                        <span> <FontAwesomeIcon icon={"search"}> </FontAwesomeIcon></span>&nbsp;
                        Search
                    </Link>
                </li>
                <li className="nav-item">
                    <Link to="/register" className="nav-link">
                        <span> <FontAwesomeIcon icon={"camera-retro"}> </FontAwesomeIcon></span>&nbsp;
                        Register
                    </Link>
                </li>
                <li className="nav-item">
                    <Link to="/login" className="nav-link">
                        <span> <FontAwesomeIcon icon={"camera-retro"}> </FontAwesomeIcon></span>&nbsp;
                        Login
                    </Link>
                </li>
                <li className="nav-item">
                    <Link to="/about" className="nav-link">
                        <span> <FontAwesomeIcon icon={"coffee"}> </FontAwesomeIcon></span>&nbsp;
                        About
                    </Link>
                </li>
            </ul>
        </nav>
    )
}

export default NavBar;