// SPDX-License-Identifier: MIT

pragma solidity >=0.6.0 <0.9.0;

contract simpleStorage {


    // by default the value will be 0
    uint256 favNum;

    struct People{
        uint256 favNum;
        string name;
    }

    People[] public people;
    mapping(string => uint256) public nameToFavNum;

    function store(uint256 myFavNum) public {
        favNum = myFavNum;
    }

    function retrieve() public view returns (uint256) {
        return favNum;
    }

    function addPerson (string memory myName, uint256 myFavNum) public {
        people.push(People(myFavNum, myName));
        nameToFavNum[myName] = myFavNum;
    }


}