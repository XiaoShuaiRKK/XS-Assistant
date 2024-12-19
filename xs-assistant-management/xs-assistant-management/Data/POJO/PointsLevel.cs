using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.Json.Serialization;
using System.Threading.Tasks;

namespace xs_assistant_management.Data.POJO
{
    public class PointsLevel
    {
        private string pointsId;
        private string pointsLevelName;
        private int pointsLevelId;
        private int nextPoints;
        private int minPoints;
        private int points;
        public PointsLevel(string pointsId, string pointsLevelName, int PointsLevelId, int points, int nextPoints, int minPoints)
        {
            this.PointsId = pointsId;
            this.PointsLevelName = pointsLevelName;
            this.PointsLevelId = PointsLevelId;
            this.Points = points;
            this.NextPoints = nextPoints;
            this.MinPoints = minPoints;
        }

        [JsonPropertyName("pointsId")]
        public string PointsId { get => pointsId; set => pointsId = value; }
        [JsonPropertyName("pointsLevelName")]
        public string PointsLevelName { get => pointsLevelName; set => pointsLevelName = value; }
        [JsonPropertyName("pointsLevel")]
        public int PointsLevelId { get => pointsLevelId; set => pointsLevelId = value; }
        [JsonPropertyName("points")]
        public int Points { get => points; set => points = value; }
        [JsonPropertyName("nextPoints")]
        public int NextPoints { get => nextPoints; set => nextPoints = value; }
        [JsonPropertyName("minPoints")]
        public int MinPoints { get => minPoints; set => minPoints = value; }
    }
}
