import React from 'react';
import axios from 'axios';
import './App.css'

class App extends React.Component{
	constructor(props){
		super(props);
		this.state={parks:[],name:'',opentime:'',closetime:'',entracefee:'',address:'',phone:'',elevation:'',errorMessage:'',successMessage:'',id:0}
		this.getAllParks()
	}
	
	
	getAllParks= async () =>{
		const resp = await axios.get(`http://localhost:8080/getAllParks`);
		this.setState({parks:resp.data});
	}

	 deletePark = async (parkid) => {
		await	axios.post('http://localhost:8080/deletePark',{id:parkid});
			this.setState({errorMessage:''})
			this.setState({successMessage:'deleted successfully'})	
		this.getAllParks()
	}
	
	populateFieldsForUpdate = async (park) => {
		this.setState({name:park.name});
		this.setState({opentime:park.opentime});
		this.setState({closetime:park.closetime});
		this.setState({entracefee:park.entracefee});
		this.setState({address:park.address});
		this.setState({phone:park.phone});
		this.setState({elevation:park.elevation});
		this.setState({id:park.id});
	}

	
	
	saveToDb= async()=>{
		
		try {
				var response= await	axios.post('http://localhost:8080/createPark',{name:this.state.name,
																			  opentime:this.state.opentime,
																			  closetime:this.state.closetime,
																			  entracefee:this.state.entracefee,
																			  address:this.state.address,
																			  phone:this.state.phone,
																			  elevation:this.state.elevation});	
				this.setState({successMessage:response.data.successMessage})	
				this.setState({errorMessage:''})					
				this.setState({name:''});
				this.setState({opentime:''});
				this.setState({closetime:''});
				this.setState({entracefee:''});
				this.setState({address:''});
				this.setState({phone:''});
				this.setState({elevation:''});
				this.setState({id:0});
		} catch (err) {
			this.setState({errorMessage:err.response.data.errorMessage})
			this.setState({successMessage:''})	
		}
		this.getAllParks()
	
	}
	
	updateToDb= async()=>{
		
		try {
				await	axios.post('http://localhost:8080/createPark',{id:this.state.id,
																				name:this.state.name,
																			  opentime:this.state.opentime,
																			  closetime:this.state.closetime,
																			  entracefee:this.state.entracefee,
																			  address:this.state.address,
																			  phone:this.state.phone,
																			  elevation:this.state.elevation});	
				this.setState({successMessage:"updated successfully"})	
				this.setState({errorMessage:''})					
		} catch (err) {
			this.setState({errorMessage:"error in updating"})
			this.setState({successMessage:''})	
		}
		this.setState({name:''});
		this.setState({opentime:''});
		this.setState({closetime:''});
		this.setState({entracefee:''});
		this.setState({address:''});
		this.setState({phone:''});
		this.setState({elevation:''});
		this.setState({id:0});
		this.getAllParks()
	}
	
	
	render(){
		return(
		<div>
			Name:<input type="text"  value={this.state.name}  onChange={event=>this.setState({name:event.target.value})}/> <br></br>
			Opentime:<input type="text"  value={this.state.opentime}  onChange={event=>this.setState({opentime:event.target.value})}/><br></br>
			CloseTime:<input type="text"  value={this.state.closetime}  onChange={event=>this.setState({closetime:event.target.value})}/><br></br>
			EntraceFee:<input type="text"  value={this.state.entracefee}  onChange={event=>this.setState({entracefee:event.target.value})}/><br></br>
			address:<input type="text"  value={this.state.address}  onChange={event=>this.setState({address:event.target.value})}/><br></br>
			Phone:<input type="text"  value={this.state.phone}  onChange={event=>this.setState({phone:event.target.value})}/><br></br>
			elevation:<input type="text"  value={this.state.elevation}  onChange={event=>this.setState({elevation:event.target.value})}/><br></br>
			
				<button onClick={this.saveToDb}> save to DB</button>
				<button onClick={this.updateToDb}> Update</button>
					<div  style={{color:'red'}}>{this.state.errorMessage}</div> 
					<div className='successMessagecolor'>{this.state.successMessage}</div>
					
					
			<table border="1">
			<tbody>
			<tr>
				<th>Name</th>
				<th>Opentime</th>
				<th>CloseTime</th>
				<th>EntraceFee</th>
				<th>address</th>
				<th>Phone</th>
				<th>elevation</th>
				<th>delete</th>
			</tr>
			
		  {this.state.parks.map(function(park){
			return(
			<tr key={park.id}>
				<td>{park.name}</td>
				<td>{park.opentime}</td>
				<td>{park.closetime}</td>
				<td>{park.entracefee}</td>
				<td>{park.address}</td>
				<td>{park.phone}</td>
				<td>{park.elevation}</td>
				<td><button onClick={()=>this.deletePark(park.id)} >delete</button></td>
				<td><button onClick={()=>this.populateFieldsForUpdate(park)} >update</button></td>
			</tr>
			)
		   },this)}
		   </tbody>
	   </table>
	   
		</div>
		
		
	  );
	}
}

export default App;


