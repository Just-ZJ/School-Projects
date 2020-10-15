clc
clear
% Version 3.3
% Date: 8 November 2019
% Players can now click on anywhere on the column to place their piece


%gets nicknames of players through user input
player1 = input('Player 1, please enter your nickname: ', 's');
player2 = input('Player 2, please enter your nickname: ', 's');
%assigns color to each player
fprintf('%s, you will be red.  \n', player1)
fprintf('%s, you will be black \n', player2)

% Initialize scene
my_scene = simpleGameEngine('ConnectFour.png',86,101);

% Set up variables to name the various sprites
empty_sprite = 1;
red_sprite = 2;
black_sprite = 3;

% Display empty board
board_display = empty_sprite * ones(6,7);
drawScene(my_scene,board_display)

for(i = 1: 1: 6*7)
    %get mouse input
    [row,col] = getMouseInput(my_scene);
    row1 = dropToBot(row,col,board_display);
    %checks if it is going out of bounds
    if(row1 < 1)
         row1 = row1+1;
    end
    %if it is out of bounds keep prompting for clicks
    while (row1 == 1) && (board_display(row1,col) ~= 1)
        [row,col] = getMouseInput(my_scene);
        row1 = dropToBot(row,col,board_display);
        if(row1 < 1)
            row1 = row1+1;
        end
    end
    
    %display updated board with player1 choice
    if(mod(i, 2) == 1)
        board_display(row1,col) = red_sprite;
    end
    
    %display updated board with player2 choice
    if(mod(i, 2) ~= 1)
        board_display(row1,col) = black_sprite;
    end
    
    %displays the updated board
    drawScene(my_scene,board_display)
    
    %checks if player 1 has won the game
    if(checkWinner(row1,col,board_display,red_sprite) == true)
        fprintf('Congratulations! %s is the winner', player1)
        break;
    end
    %checks if player 2 has won the game
    if(checkWinner(row1,col,board_display,black_sprite) == true)
        fprintf('Congratulations! %s is the winner', player2)
        break;
    end
end

%drops the piece to the next avaialbe box in the column. stacks on top of
%pieces
function [xAxis] = dropToBot(x,y,board_display)
xAxis = 0;
%checks from row 1 to 6 and finds the lowest row that has value 1 
for(i = 1:6)
    if(board_display(i,y) == 1)
        xAxis = i;
    end
end
end

%returns true if num is more than 4, false if not 4
function x = check4(num)
x = false;
%assigns x with true if num is more than 4.
if(num >= 4)
    x = true;
end
end

%checks for horizontal wins
function win = checkHorizontal(x,y,board_display,player)
%values of the total rows and columns in the board
totRow = 6;
totCol = 7;
win = false;

for(i = 1:1:totRow)
    if(board_display(x,y) == player)
        if( y <= totCol && y >= 1)
            %checks to the right of box
            j = 0;
            while(board_display(x,y + j) == player)
                j = j + 1;
                %check if there is 4 pieces in a row in right direction
                win = check4(j);
                %break if y is going out of array
                if( y+j > totCol)
                    break;
                end
            end
            %checks to the left of box
            k = 0;
            while(board_display(x,y - k) == player)
                k = k + 1;
                %check if there is 4 pieces in a row in both direction
                win = check4(j+k-1);
                %break if y is going out of array
                if(y-k <= 0)
                    break;
                end
            end
        end
    end
end

end

%checks for vertical wins
function win = checkVertical(x,y,board_display,player)
%values of the total rows and columns in the board
totRow = 6;
totCol = 7;
win = false;

for(i = 1:1:totCol)
    if(board_display(x,y) == player)
        %checks if x is 1-6
        if( x <= totRow && x >= 1)
            %checks to the bottom of box
            j = 0;
            while(board_display(x+j,y) == player)
                j = j + 1;
                %check if there is 4 pieces in a row in the bottom direction
                win = check4(j);
                %break if x is going out of array
                if( x+j > totRow)
                    break;
                end
            end
            %checks to the top of box
            k = 0;
            while(board_display(x-k,y) == player)
                k = k + 1;
                %check if there is 4 pieces in a row in both direction
                win = check4(j+k-1);
                %break if x is going out of array
                if(x-k <= 0)
                    break;
                end
            end
        end
    end
end
end

%checks for diagonal right wins
function win = checkDiagRight(x,y,board_display,player)
%values of the total rows and columns in the board
totRow = 6;
totCol = 7;
win = false;

for(i = 1:1:totCol)
    if(board_display(x,y) == player)
        if( x <= totRow && x >= 1 &&  y <= totCol && y >= 1)
            %checks to the diagonal right upwards of box
            j = 0;
            while(board_display(x-j,y+j) == player)
                j = j + 1;
                %check if there is 4 pieces in the diagonally right upwards direction
                win = check4(j);
                %break if x & y is going out of array
                if( x-j <= 0 || y+j > totCol)
                    break;
                end
            end
            %checks to the diagonal left downwards of box
            k = 0;
            while(board_display(x+k,y-k) == player)
                k = k + 1;
                %check if there is 4 pieces in a row in both direction
                win = check4(j+k-1);
                %break if x & y is going out of array
                if(x+k > totRow || y-k <= 0)
                    break;
                end
            end
        end
    end
end
end

%checks for diagonal left wins
function win = checkDiagLeft(x,y,board_display,player)
%values of the total rows and columns in the board
totRow = 6;
totCol = 7;
win = false;

for(i = 1:1:totCol)
    if(board_display(x,y) == player)
        %horizontal win
        if( x <= totRow && x >= 1 &&  y <= totCol && y >= 1)
            %checks to the diagonal left upwards of box
            j = 0;
            while(board_display(x-j,y-j) == player)
                j = j + 1;
                %check if there is 4 pieces in the diagonally left upwards direction
                win = check4(j);
                %break if x & y is going out of array
                if( x-j <= 0 || y-j <= 0)
                    break;
                end
            end
            %checks to the diagonal right downwards of box
            k = 0;
            while(board_display(x+k,y+k) == player)
                k = k + 1;
                %check if there is 4 pieces in a row in both direction
                win = check4(j+k-1);
                %break if x & y is going out of array
                if(x+k > totRow || y+k > totCol)
                    break;
                end
            end
        end
    end
end
end

%checks for overall wins
function win = checkWinner(x,y,board_display,player)
win = checkHorizontal(x,y,board_display,player);
%checks for vertical win if win is false.
if(win == false)
    win = checkVertical(x,y,board_display,player);
end
%checks for diagonally right win if win is false.
if(win == false)
    win = checkDiagRight(x,y,board_display,player);
end
%checks for diagonally left win if win is false.
if(win == false)
    win = checkDiagLeft(x,y,board_display,player);
end
end
