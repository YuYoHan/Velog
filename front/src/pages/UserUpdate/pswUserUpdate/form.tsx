   import React, { ChangeEvent, useState } from 'react'
import styled from 'styled-components'
import { maskingName } from './hook/PswEncryption'
import { AiFillEye } from "react-icons/ai";
import useCheckPassword from 'hooks/checkPassword';
import { userPswdataPrpos } from '../Index';


function UserPswform({password,userPw,onChangeForm} : userPswdataPrpos) {

   /*
      PswEncryption - 비밀번호 노출 방지 ••• 처리 hook
      PswEdit - 수정하기 버튼 클릭 시 div -> input 태그 용도
      disabled - 비밀번호 8글자 이상 hook
   */
   const PswEncryption = maskingName(userPw)
   const [PswEdit, setPswEdit] = useState<boolean>(false)
   const disabled = useCheckPassword(userPw)
   

   const onClickPswUpdate = () => {
      setPswEdit(true)
   }
   
   const onChnagePswEdit = () => {
      if(disabled) return setPswEdit(false)
      alert('8글자 이상 입력해주세요')
   }

  return (
    <Wrapper>
      <PswTitle >
         {
            !PswEdit ? 
            PswEncryption : <Input type='password' name='userPw' value={userPw}  onChange={onChangeForm}/>
         }
      </PswTitle>
      <EditWrapper>
         {
            PswEdit ?
            <button onClick={onChnagePswEdit}>저장</button>
            :
            <button onClick={onClickPswUpdate}>수정</button>
         }
      </EditWrapper>
    </Wrapper>
  )
}

export default UserPswform
   
const Wrapper = styled.div`
   flex: 1 1 0%;
   display: flex;
   align-items: center;

   
`
const PswTitle = styled.div`
   flex: 1 1 0%;
   font-size: 1rem;
   color: #495057;
   line-height: 1.5;
   display: flex;
   align-items: center;
   
   & button{
      background: none;
      cursor : pointer;
      padding-top : 5px;
      margin-left : .5rem
   }
`
const EditWrapper = styled.div`
   display: flex;
   align-items: center;
   margin-left: 1rem;

   

   & button{
      outline: none;
      padding: 0px;
      border: none;
      display: inline;
      font-size: 1rem;
      line-height: 1.5;
      color: #757bf6;
      text-decoration: underline;
      background: none;
      cursor: pointer;
   }
`
const Input = styled.input`
   display: block;
   border: 1px solid #DEE2E6;
   background: #FFFFFF;
   padding: 0.5rem;
   color: #495057;
   font-size: 1rem;
   line-height: 1rem;
   outline: none;
   border-radius: 4px;
   flex: 1 1 0%;
   margin-right: 1rem;
`