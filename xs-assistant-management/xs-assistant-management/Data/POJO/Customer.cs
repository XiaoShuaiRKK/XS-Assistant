using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.Json.Serialization;
using System.Threading.Tasks;

namespace xs_assistant_management.Data.POJO
{
    public class Customer
    {
        int id;
        string firstName;
        string lastName;
        string email;
        DateTime birth;
        string idNumber;
        int stateId;
        int level;
        string iconPath;

        public Customer(int id, string firstName, string lastName, string email,
            DateTime birth, string idNumber, int stateId, int level, string iconPath)
        {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.birth = birth;
            this.idNumber = idNumber;
            this.stateId = stateId;
            this.level = level;
            this.iconPath = iconPath;
        }

        [JsonPropertyName("id")]
        public int Id { get => id; set => id = value; }
        [JsonPropertyName("firstName")]
        public string FirstName { get => firstName; set => firstName = value; }
        [JsonPropertyName("lastName")]
        public string LastName { get => lastName; set => lastName = value; }
        [JsonPropertyName("email")]
        public string Email { get => email; set => email = value; }
        [JsonPropertyName("birth")]
        public DateTime Birth { get => birth; set => birth = value; }
        [JsonPropertyName("idNumber")]
        public string IdNumber { get => idNumber; set => idNumber = value; }
        [JsonPropertyName("stateId")]
        public int StateId { get => stateId; set => stateId = value; }
        [JsonPropertyName("level")]
        public int Level { get => level; set => level = value; }
        [JsonPropertyName("iconPath")]
        public string IconPath { get => iconPath; set => iconPath = value; }
    }
}
