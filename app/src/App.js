import React, { Component } from "react";
import Spinner from 'react-bootstrap/Spinner'
import "./App.css";
import UserDetails from "./Components/UserDetails";
import 'bootstrap/dist/css/bootstrap.min.css';

class App extends Component {
  state = {
    userdata: [],
    userPostComments: [],
    spinnerState:true,
    currentPost:"",
    previousPost:"",
    sort: "none",
    filter: "none",
    errorMsg: "",
  };

  componentDidMount() {
    this.handleGetUserData();
  }


  handleGetUserData = () => {
    console.log("handleGetData invoked");
    fetch("http://localhost:8080/api/usersAndPosts", {
    headers : { 
      'Content-Type': 'application/json',
      'Accept': 'application/json',
      'Access-Control-Allow-Origin': '*'
     }

  })
    .then(res=>res.json())
    .then(
      (result)=> {
          console.log(result);
          result=result.map(user=>{
            user.shortname=user.name;
            if(user.name.includes("Mrs"))
              user.shortname=user.name.replace('Mrs. ','');
            if(user.shortname.includes(" "))
              user.shortname=user.shortname.substr(0,user.shortname.indexOf(' '))
              //console.log(user.name);
            return user
          });
        setTimeout(() => {
          this.setState({
            userdata:result,
            spinnerState:false
          });
      }, 1000);
      }
    )
  };

  

  persistPrevPostClick = (e) => {

    console.log("user id:"+e);
    this.setState({
      previousPost:this.state.currentPost,
      currentPost:e
    });
    
  };


  render() {
    return (
      <div>
        <header className="Header">
        <h1>Blog Demo</h1>
        </header>
        <div className="Body">
        <UserDetails userdata={this.state.userdata} persistPrevPostClick={this.persistPrevPostClick} previousPostId={this.state.previousPost}  />
        </div>
        <div className="spin">{this.state.spinnerState ? <Spinner animation="border" variant="info" /> : null}</div>
      </div>
    );
  }
}

export default App;
