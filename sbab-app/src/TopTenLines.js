import React from "react";
import { Container } from "react-bootstrap";
import StopNames from "./StopNames";
export default class TopTenLines extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      url: "http://localhost:8080/api/toplines",
      loading: true,
      items: [],
      frames: 180000,
      fetch: false,
    };
  }
  async componentDidMount() {
    fetchTopLines(this.state).then((item) => {
      this.setState({
        items: item,
        loading: false,
      });
    });
  }
  static getDerivedStateFromProps(nextProps, state) {
    return state;
  }
  render() {
    const { loading, items } = this.state;
    if (loading) {
      return <div>Content is loading ... </div>;
    } else {
      return (
        <React.Fragment>
          <Container>
            <div>
              <br />
            <h1>SBAB Assignment</h1>
            <h3>Top ten buss lines </h3>
            <p>Click on the line number to see the names of the stops</p>
              <ul>
                {items.map((item, index) => (
                  <li key={index}>
                    <StopNames lineNumber={item.lineNumber} />
                  </li>
                ))}
              </ul>
            </div>
          </Container>
        </React.Fragment>
      );
    }
  }
}
async function fetchTopLines(state) {
  const response = await fetch(state.url);
  const items = await response.json();
  return items;
}
