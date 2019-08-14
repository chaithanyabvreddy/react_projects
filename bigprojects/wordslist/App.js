import React from 'react';
import axios from 'axios';
class App extends React.Component{
	
	constructor(props){
			super(props);
			this.state={'wordslist':[], 'wordsListIndex':0,'currentWord':''}
		
	}
	
	
	componentWillMount = async ()=>{
			await this.getWordsList();
	}
	
		
	displayNextword=()=>{
		if(this.state.wordsListIndex<this.state.wordslist.length-1){
			this.setState({'wordsListIndex':this.state.wordsListIndex+1});
		}else{
			this.setState({'wordsListIndex':0});
		}
		this.setState({'currentWord':this.state.wordslist[this.state.wordsListIndex].wordName});
	}
	
		
	getWordsList = async () =>{
	  const resp = await axios.get(`http://localhost:8080/getAllWords`);
	  this.setState({'wordslist':resp.data});
	  this.setState({'currentWord':this.state.wordslist[0].wordName});
	}

	
	
	render(){
	  return (
		  <div>
			  <center> 
			  {this.state.currentWord}<br></br>
				<button onClick={this.displayNextword}>	NEXT</button>	
			</center>
		  </div>
		);
	}
}
export default App;
