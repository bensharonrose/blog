import React from "react";

function Comment({commentData,spinnerState}) {
  return (
    <div className="CommentBlock">
           
            {commentData.map(comment=>(
            
              <div className="CommentSection" key={comment.id}>
                <span>{comment.name}({comment.email})</span>
                <p>{comment.body}</p>
              </div>
            
                ))}
    </div>
  );
}

export default Comment;
