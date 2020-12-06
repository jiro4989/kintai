import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import reportWebVitals from './reportWebVitals';
import { BrowserRouter as Router, Route } from 'react-router-dom';

import App from './App';
import Signup from './Signup';
import Signin from './Signin';

ReactDOM.render(
  <React.StrictMode>
    <div className="Index">
      <Router>
        <div>
          <Route path='/' component={App} />
          <Route path='/signup' component={Signup} />
          <Route path='/signin' component={Signin} />
        </div>
      </Router>
    </div>
  </React.StrictMode>,
  document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
