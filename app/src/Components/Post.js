import React from "react";
import Comment from "./Comment";
import Spinner from 'react-bootstrap/Spinner'
import plusBtn from "../images/plus_button.png";
import minusBtn from "../images/minus_button.png";

const { useState,Fragment } = React;

function Post({name,postdata}) {

    const [shownComments, setShownComments] = useState({});
    const [commentData, setCommentData] = useState({});
    const [spinnerState, setSpinnerState] = useState(false);
    const [toggleComment, setToggleComment] = useState(false);

    const getUserPostComments = (postId) => {
      setToggleComment(prevToggleComment => ({
        ...prevToggleComment,
        [postId]: !prevToggleComment[postId]
      }));
        //setSpinnerState[postId](true);
        setSpinnerState(prevSpinnerState => ({
          ...prevSpinnerState,
          [postId]: !prevSpinnerState[postId]
        }));
        
        console.log("getUserPostComments invoked");
       fetch("http://localhost:8080/api/comments?postId="+postId, {
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
            

              setTimeout(() => {
                console.log('This will run after 1 second!')
                setCommentData(result);
                setShownComments(prevShownComments => ({
                    ...prevShownComments,
                    [postId]: !prevShownComments[postId]
                  }));
                setSpinnerState(prevSpinnerState => ({
                  ...prevSpinnerState,
                  [postId]: !prevSpinnerState[postId]
                }));
              }, 1000);
              
          }
        )
      };

    

  return (
    <div>
            <h3>Posts from {name}:</h3>
            {postdata.map(post=>(
            <Fragment key="{post.id}_fragment">
              
              <div className="PostSection" key="{post.id}_title">
                
                  <div className="PostInnerSection">
                  <span>{post.title}</span>
                  <p>{post.body}</p>
                  </div>
                  {toggleComment[post.id] ?
                  <img src={minusBtn} alt="minusBtn" key="{post.id}_minusBtn" onClick={()=>getUserPostComments(post.id)} />:
                  <img src={plusBtn} alt="plusBtn" key="{post.id}_plusBtn" onClick={()=>getUserPostComments(post.id)} />}
                  {spinnerState[post.id] ?<Spinner animation="border" variant="info" />:null}
              </div>
              
              <div key="{post.id}_comments">
                  {shownComments[post.id] ?
                  <Comment commentData={commentData} spinnerState={spinnerState} />:null}
              </div>
            </Fragment>
                ))}
    </div>
  );
}

export default Post;
