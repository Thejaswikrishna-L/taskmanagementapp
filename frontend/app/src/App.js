import { useEffect, useState } from 'react';
import './App.css';
import TodoItem from "./components/todoItem" ;

function App() {
  const [todoItems, setTodoItems] = useState(null);

  useEffect(()=> {
    //do something on lode
    console.log("Hey, I have loaded up");

    if(!todoItems){
      fetch('http://localhost:2345/api/todoItems')
      .then((response) => response.json())
      .then((data) => {
        console.log("Todo items list:", data);
        setTodoItems(data);
      });
    }
   
  }, [todoItems]);

  function addNewTodoItem (){
    fetch("http://localhost:2345/api/todoItems", {
      headers : {
        "content-type": "application/json"
      },
      method: "POST",
    }).then((response) => response.json())
      .then((aTodoItem) => { 
              
        setTodoItems([...todoItems, aTodoItem ]);
    });
  }
  
  function handleDeleteTodoItem (item) {
    const updatedTodoItems = todoItems.filter(aTodoItem => aTodoItem.id !== item.id);
    console.log('updated todo items', updatedTodoItems);
    setTodoItems([...updatedTodoItems]);
  }
  // ternary operator
  // if(something)
  //   do item 1
  // else
  //   do item 2
  // something ? (do item 1):(do item 2)
  return (
    <div className="App">
      <header>
        <form id="to-do-form">
        <button onClick={addNewTodoItem}> Add New Task</button>
        </form>
      </header>
          <div>
          {todoItems
          ?todoItems.map((todoItem) => {
            return <TodoItem key={todoItem.id} data={todoItem} emitDeleteTodoItem={handleDeleteTodoItem} />;
          })
          : "loading data..."}
          </div>  
    </div>
  );
}

export default App;
