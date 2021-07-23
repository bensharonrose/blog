import React from "react";
import Post from "./Post";
import { Button } from 'react-bootstrap';
const { useState, useEffect,Fragment } = React;

function UserDetails({userdata,persistPrevPostClick,previousPostId}) {

  const [shownPosts, setShownPosts] = useState({});
  const [morePosts, setMorePosts] = useState(false);

  const onUserPostClick = id => {
    persistPrevPostClick(id);
    console.log("Previous post id:"+previousPostId);
    setShownPosts(
      {previousPostId: 0}
    );

    setMorePosts(
      {previousPostId: 0}
    );

    setShownPosts(prevShownPosts => ({
      ...prevShownPosts,
      [id]: !prevShownPosts[id]
    }));
  };

  const onMorePostClick = id => {
    console.log("onMorePostClick invoked");
    setMorePosts(prevMorePosts => ({
      ...prevMorePosts,
      [id]: !prevMorePosts[id]
    }));
  };


  return (
    <div className="UserSection">
            <h2>Please select a user to find their posts:</h2>
            {userdata.map(user=>(
              shownPosts[user.id] ? 
              <div className="UserBoxSelected" onClick={()=>onUserPostClick(user.id)} key={user.id}>
                  {user.shortname}
              </div>:<div className="UserBox" onClick={()=>onUserPostClick(user.id)} key={user.id}>
                  {user.shortname}
              </div>
                ))}

              {userdata.map(user=>(
                shownPosts[user.id] ? 
                  <Fragment>
                  {morePosts[user.id] ? 
                    <Fragment>
                    <Post id={user.id} key="{user.id}_post" name={user.name} postdata={user.posts} />
                    <Button className="morePostBtn" variant="primary" onClick={()=>onMorePostClick(user.id)}>...Show less</Button>
                    </Fragment>
                    :<Fragment><Post id={user.id} key="{user.id}_post" name={user.name} postdata={user.posts.slice(0, 3)} />
                      <Button className="morePostBtn" variant="primary" onClick={()=>onMorePostClick(user.id)}>...Load All</Button></Fragment>}
                  </Fragment>:null
              ))}
    </div>
  );
}

export default UserDetails;
